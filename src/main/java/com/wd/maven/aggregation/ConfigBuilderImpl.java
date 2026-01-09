package com.wd.maven.aggregation;

import com.intellij.openapi.application.ApplicationInfo;
import java.io.File;

/**
 * @author solom
 * @ClassName ConfigBuilderImpl
 * @Description
 * @Version 1.0.0
 * @create 2022-02-21 14:07
 */
public class ConfigBuilderImpl extends BaseBuilder {
  private RedisConfigBuilderImpl redisConfigBuilder = new RedisConfigBuilderImpl();
  private SwaggerBuilderImpl swaggerBuilder = new SwaggerBuilderImpl();

  public void generation(PropertiesConfig config, String projectsRoot) throws Exception {
    PropertiesConfig tmpConfig = new PropertiesConfig();
    tmpConfig.setArtifactId(config.getArtifactId());
    tmpConfig.setParentArtifactId(config.getParentArtifactId());
    tmpConfig.setPackageName((config.getGroupId() + "." + config.getParentArtifactId() + "."
        + config.getArtifactId()+".config").replace("-", "_"));
    tmpConfig.setGroupId(config.getGroupId());
    redisConfigBuilder.generation(config, projectsRoot);
    tmpConfig.setConfig(config);
    String packagePath = tmpConfig.getPackageName().replace(".", "/");
    File file = new File(projectsRoot + "/src/main/java/" + packagePath, "ControllerResponseBodyAdvice.java");
    // 写入文件
    super.writeFile(file, "controller_response_body_advice.ftl", tmpConfig);
    swaggerBuilder.generation(config, projectsRoot);
  }
}
