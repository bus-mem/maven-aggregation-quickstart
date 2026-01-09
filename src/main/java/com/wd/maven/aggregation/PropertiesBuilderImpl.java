package com.wd.maven.aggregation;

import java.io.File;

/**
 * @author solom
 * @ClassName GenerationProperties
 * @Description
 * @Version 1.0.0
 * @create 2022-02-17 18:16
 */
public class PropertiesBuilderImpl extends BaseBuilder {

  public void generation(PropertiesConfig config, String projectsRoot) throws Exception {
    File file = new File(projectsRoot + "/src/main/resources/", "application.properties");
    // 写入文件
    super.writeFile(file, "application.properties.ftl", config);
  }
}
