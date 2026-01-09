package com.wd.maven.aggregation;

import com.intellij.openapi.application.ApplicationInfo;
import java.io.File;

/**
 * @author solom
 * @ClassName SwaggerBuilderImpl
 * @Description
 * @Version 1.0.0
 * @create 2022-02-21 14:59
 */
public class SwaggerBuilderImpl extends BaseBuilder {

  public void generation(PropertiesConfig config, String projectsRoot) throws Exception {
    PropertiesConfig tmpConfig = new PropertiesConfig();
    tmpConfig.setArtifactId(config.getArtifactId());
    tmpConfig.setParentArtifactId(config.getParentArtifactId());
    tmpConfig.setPackageName((config.getGroupId() + "." + config.getParentArtifactId() + "."
        + config.getArtifactId()+".config").replace("-", "_"));
    tmpConfig.setGroupId(config.getGroupId());
    tmpConfig.setConfig(config);
    String packagePath = tmpConfig.getPackageName().replace(".", "/");
    File file = new File(projectsRoot + "/src/main/java/" + packagePath, "OpenApiConfig.java");
    // 写入文件
    super.writeFile(file, "swagger/open_api_config.ftl", tmpConfig);
  }
}
