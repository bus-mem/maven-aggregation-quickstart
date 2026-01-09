package com.wd.maven.aggregation;

import com.intellij.ide.plugins.PluginManager;
import com.intellij.ide.plugins.PluginManagerCore;
import com.intellij.ide.plugins.PluginUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.util.PathUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/*
 * ProjectName: maven-aggregation
 * PackageName: com.wd.maven.aggregation
 * Description:
 * CreateBy: Solom
 * Email: solomo.kin@gmail.com
 * Copyright: Solom
 * CreatedTime: 2026-01-05 15:36:15:36
 */
public class RepositoryFilesGenerator extends BaseBuilder{

  public void generation(PropertiesConfig config, String projectsRoot) throws Exception {
    PomBuilderImpl pomBuilder = new PomBuilderImpl();
    PropertiesConfig repositoryConfig = new PropertiesConfig();
    repositoryConfig.setDatasource(config.getDatasource());
    repositoryConfig.setRedis(config.getRedis());
    repositoryConfig.setPackageName((config.getGroupId() + "." + config.getArtifactId() + ".repository").replace("-", "_"));
    repositoryConfig.setGroupId(config.getGroupId());
    repositoryConfig.setArtifactId("repository");
    repositoryConfig.setDescription("repository");
    repositoryConfig.setParentArtifactId(config.getParentArtifactId());
    repositoryConfig.setConfig(config);
    File f = new File(projectsRoot + "/src/main/resources/entity.java.ftl");
    f.getParentFile().mkdirs();
    Path copied = f.toPath();
    InputStream inputStream = RepositoryFilesGenerator.class.getResourceAsStream("/templates/swagger/entity.java.ftl");
    Files.copy(inputStream, copied, StandardCopyOption.REPLACE_EXISTING);
    f = new File(projectsRoot + "/src/main/resources/serviceImpl.java.ftl");
    f.getParentFile().mkdirs();
    copied = f.toPath();
    inputStream = RepositoryFilesGenerator.class.getResourceAsStream("/templates/swagger/serviceImpl.java.ftl");
    Files.copy(inputStream, copied, StandardCopyOption.REPLACE_EXISTING);
    pomBuilder.generation(repositoryConfig, projectsRoot, "pom_repository_final.ftl");
    CodeGeneratorBuilderImpl codeGeneratorBuilder = new CodeGeneratorBuilderImpl();
    codeGeneratorBuilder.generation(repositoryConfig, projectsRoot);
  }
}
