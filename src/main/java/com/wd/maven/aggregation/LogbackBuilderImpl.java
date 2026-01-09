package com.wd.maven.aggregation;

import java.io.File;

/**
 * @author solom
 * @ClassName LogbackBuilderImpl
 * @Description
 * @Version 1.0.0
 * @create 2022-02-21 10:18
 */
public class LogbackBuilderImpl extends BaseBuilder{

  public void generation(PropertiesConfig config, String projectsRoot) throws Exception {
    File file = new File(projectsRoot + "/src/main/resources/", "logback-spring.xml");
    // 写入文件
    super.writeFile(file, "logback-spring.ftl", config);
  }
}
