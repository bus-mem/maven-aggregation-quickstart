package com.wd.maven.aggregation;

import java.io.File;
import java.util.Arrays;

/**
 * @author solom
 * @ClassName ApplicationBuilderImpl
 * @Description
 * @Version 1.0.0
 * @create 2022-02-18 17:34
 */
public class ApplicationBuilderImpl extends BaseBuilder {

  public void generation(PropertiesConfig config, String projectsRoot) throws Exception {
    PropertiesConfig tmpConfig = new PropertiesConfig();
    tmpConfig.setArtifactId(config.getArtifactId());
    tmpConfig.setParentArtifactId(config.getParentArtifactId());
    tmpConfig.setPackageName((config.getGroupId() + "." + config.getParentArtifactId() + "."
        + config.getArtifactId()).replace("-", "_"));
    tmpConfig.setGroupId(config.getGroupId());
    //启动类名称
    String[] split = config.getArtifactId().split("-");
    StringBuffer applicationJavaName = new StringBuffer();
    Arrays.asList(split).forEach(s -> {
      applicationJavaName.append(s.substring(0, 1).toUpperCase() + s.substring(1));
    });
    applicationJavaName.append("Application");
    config.setApplicationJavaName(applicationJavaName.toString());
    String packagePath = tmpConfig.getPackageName().replace(".", "/") + "/";
    File file = new File(projectsRoot + "/src/main/java/" + packagePath,
        applicationJavaName + ".java");
    // 写入文件
    super.writeFile(file, "application.ftl", tmpConfig);
  }
}
