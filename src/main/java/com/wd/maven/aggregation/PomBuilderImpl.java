package com.wd.maven.aggregation;

import java.io.File;

/**
 * @author solom
 * @ClassName PomBuilderImpl
 * @Description
 * @Version 1.0.0
 * @create 2022-02-18 17:42
 */
public class PomBuilderImpl extends BaseBuilder {

  public void generation(PropertiesConfig config, String projectsRoot, String tmpFile) throws Exception {
    File file = new File(projectsRoot, "pom.xml");
    // 写入文件
    super.writeFile(file, tmpFile, config);
  }
}
