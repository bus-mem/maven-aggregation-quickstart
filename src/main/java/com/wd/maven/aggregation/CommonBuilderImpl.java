package com.wd.maven.aggregation;

import com.intellij.openapi.application.ApplicationInfo;
import java.io.File;

/**
 * @author solom
 * @ClassName CommonBuilderImpl
 * @Description
 * @Version 1.0.0
 * @create 2022-02-21 11:01
 */
public class CommonBuilderImpl extends BaseBuilder {
  public void generation(PropertiesConfig config, String projectsRoot) throws Exception {
    PropertiesConfig tmpConfig = new PropertiesConfig();
    tmpConfig.setArtifactId(config.getArtifactId());
    tmpConfig.setParentArtifactId(config.getParentArtifactId());
    tmpConfig.setPackageName((config.getGroupId() + "." + config.getParentArtifactId() + "."
        + config.getArtifactId()+".common").replace("-", "_"));
    tmpConfig.setGroupId(config.getGroupId());
    String packagePath = tmpConfig.getPackageName().replace(".", "/");
    File file = new File(projectsRoot + "/src/main/java/" + packagePath, "Common.java");
    // 写入文件
    super.writeFile(file, "common/common.ftl", tmpConfig);
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "Msg.java");
    // 写入文件
    super.writeFile(file, "common/msg.ftl", tmpConfig);
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "ImageCode.java");
  }
}
