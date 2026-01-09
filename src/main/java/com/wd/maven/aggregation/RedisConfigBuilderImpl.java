package com.wd.maven.aggregation;

import com.intellij.openapi.application.ApplicationInfo;
import java.io.File;

/**
 * @author solom
 * @ClassName RedisConfigBuilderImpl
 * @Description
 * @Version 1.0.0
 * @create 2022-02-21 10:51
 */
public class RedisConfigBuilderImpl extends BaseBuilder {

  public void generation(PropertiesConfig config, String projectsRoot) throws Exception {
    PropertiesConfig tmpConfig = new PropertiesConfig();
    tmpConfig.setArtifactId(config.getArtifactId());
    tmpConfig.setParentArtifactId(config.getParentArtifactId());
    tmpConfig.setPackageName((config.getGroupId() + "." + config.getParentArtifactId() + "."
        + config.getArtifactId()+".config").replace("-", "_"));
    tmpConfig.setGroupId(config.getGroupId());
    tmpConfig.setConfig(config);
    String packagePath = tmpConfig.getPackageName().replace(".", "/");
    File file = new File(projectsRoot + "/src/main/java/" + packagePath, "RedisConfig.java");
    // 写入文件
    super.writeFile(file, "redis/redis.ftl", tmpConfig);
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "GsonRedisSerializer.java");
    super.writeFile(file, "redis/gson.ftl", tmpConfig);
  }
}
