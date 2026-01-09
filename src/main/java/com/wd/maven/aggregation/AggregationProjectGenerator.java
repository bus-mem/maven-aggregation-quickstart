package com.wd.maven.aggregation;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;

import com.wd.maven.aggregation.PropertiesConfig.Datasource;
import com.wd.maven.aggregation.PropertiesConfig.Mysql;
import com.wd.maven.aggregation.PropertiesConfig.Redis;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class AggregationProjectGenerator {

    private static final Logger LOG = Logger.getInstance(AggregationProjectGenerator.class);
    /**
     * 生成项目，支持单模块DDD架构和多模块架构
     * @param project 项目
     * @param groupId 组织ID
     * @param artifactId 项目ID
     */
    public void generateProject(Project project, String groupId, String artifactId) {
        String baseDir = project.getBasePath();
        if ( baseDir == null || baseDir.isEmpty()) {
            LOG.warn("Project base directory not found.");
            return;
        }

        Path projectPath = Paths.get(baseDir);

        try {
          // 多模块架构（原有逻辑）
          LOG.info("开始生成Maven聚合项目: " + artifactId);

          PropertiesConfig config = new PropertiesConfig();
          config.setGroupId(groupId);
          config.setArtifactId(artifactId);
          config.setParentArtifactId(artifactId);
          Redis redis = new Redis();
          redis.setHost("127.0.0.1");
          redis.setPort("6379");
          redis.setPassword("");
          redis.setDatabase("0");
          config.setRedis(redis);
          Mysql mysql = new Mysql();
          mysql.setUrl(
              "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=convertToNull");
          mysql.setUsername("root");
          mysql.setPassword("root");
          Datasource datasource = new Datasource();
          datasource.setMysql(mysql);
          config.setDatasource(datasource);

          // 1. 先创建所有目录结构
          createAllDirectoryStructures(projectPath, artifactId);
          PomBuilderImpl pomBuilder = new PomBuilderImpl();
          pomBuilder.generation(config, projectPath.toString(), "pom_parent.ftl");
          // 3. 写入子模块POM（包含依赖关系）
          Path modulePath = projectPath.resolve("repository");
          RepositoryFilesGenerator repositoryFilesGenerator = new RepositoryFilesGenerator();
          repositoryFilesGenerator.generation(config, modulePath.toString());
          modulePath = projectPath.resolve("api");
          ApiBuilder builder = new ApiBuilder();
          builder.generation(config, modulePath.toString());
          writeGitignore(projectPath);

          LOG.info("Maven聚合项目生成完成: " + artifactId);
        } catch (Exception e) {
            LOG.error("生成项目时发生错误", e);
        }
    }

    /**
     * 创建所有目录结构（多模块）
     */
    private void createAllDirectoryStructures(Path projectPath, String artifactId) {
      Path modulePath = projectPath.resolve("repository");
      createDirectoryStructure(modulePath.resolve("src/main/java"));
      createDirectoryStructure(modulePath.resolve("src/test/java"));
      createDirectoryStructure(modulePath.resolve("src/main/resources"));
      modulePath = projectPath.resolve("api");
      createDirectoryStructure(modulePath.resolve("src/main/java"));
      createDirectoryStructure(modulePath.resolve("src/test/java"));
      createDirectoryStructure(modulePath.resolve("src/main/resources"));
    }

    private void writeGitignore(Path projectPath) {
        String content = "# Maven\n" +
                "target/\n" +
                "pom.xml.tag\n" +
                "pom.xml.releaseBackup\n" +
                "pom.xml.versionsBackup\n" +
                "pom.xml.next\n" +
                "release.properties\n" +
                "dependency-reduced-pom.xml\n" +
                "buildNumber.properties\n" +
                ".mvn/timing.properties\n" +
                ".mvn/wrapper/maven-wrapper.jar\n\n" +
                "# IntelliJ IDEA\n" +
                ".idea/\n" +
                "*.iws\n" +
                "*.iml\n" +
                "*.ipr\n\n" +
                "# Eclipse\n" +
                ".classpath\n" +
                ".project\n" +
                ".settings/\n\n" +
                "# Mac\n" +
                ".DS_Store\n";

        writeToFile(projectPath.resolve(".gitignore"), content);
    }

    private void writeToFile(Path path, String content) {
        try {
            // 创建父目录（如果不存在）
            Files.createDirectories(path.getParent());

            // 写入文件内容
            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                writer.write(content);
            }
        } catch (IOException e) {
            LOG.warn("Failed to write file: " + path, e);
        }
    }

    private void createDirectoryStructure(Path path) {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            LOG.warn("Failed to create directory: " + path, e);
        }
    }
}