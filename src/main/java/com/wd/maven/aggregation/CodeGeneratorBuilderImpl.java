package com.wd.maven.aggregation;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @author solom
 * @ClassName CodeGeneratorBuilderImpl
 * @Description
 * @Version 1.0.0
 * @create 2022-02-22 16:13
 */
public class CodeGeneratorBuilderImpl extends BaseBuilder{
  public void generation(PropertiesConfig config, String projectsRoot) throws Exception {
    String packagePath = config.getPackageName().replace(".", "/") + "/";
    File file = new File(projectsRoot + "/src/main/java/" + packagePath, "CodeGenerator.java");
    // 写入文件
    writeFile(file, "code_generator.ftl", config);

    file = new File(projectsRoot + "/src/main/resources/");
    file.mkdir();

    packagePath = config.getPackageName().replace(".", "/") + "/common/";
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "BeanConverter.java");
    writeFile(file, "code/common/bean_converter.ftl", config);

    packagePath = config.getPackageName().replace(".", "/") + "/entity/convert/";
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "Convert.java");
    writeFile(file, "code/entity/convert.ftl", config);

    packagePath = config.getPackageName().replace(".", "/") + "/modelmapper/jdk8/";
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "FromOptionalConverter.java");
    writeFile(file, "code/modelmapper/jdk8/from_optional_converter.ftl", config);

    packagePath = config.getPackageName().replace(".", "/") + "/modelmapper/jdk8/";
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "Jdk8Module.java");
    writeFile(file, "code/modelmapper/jdk8/jdk8_module.ftl", config);

    packagePath = config.getPackageName().replace(".", "/") + "/modelmapper/jdk8/";
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "ToOptionalConverter.java");
    writeFile(file, "code/modelmapper/jdk8/to_optional_converter.ftl", config);

    packagePath = config.getPackageName().replace(".", "/") + "/modelmapper/jsr310/";
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "FromTemporalConverter.java");
    writeFile(file, "code/modelmapper/jsr310/from_temporal_converter.ftl", config);

    packagePath = config.getPackageName().replace(".", "/") + "/modelmapper/jsr310/";
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "Jsr310ModuleConfig.java");
    writeFile(file, "code/modelmapper/jsr310/jsr310_module_config.ftl", config);

    packagePath = config.getPackageName().replace(".", "/") + "/modelmapper/jsr310/";
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "Jsr310Module.java");
    writeFile(file, "code/modelmapper/jsr310/jsr310_module.ftl", config);

    packagePath = config.getPackageName().replace(".", "/") + "/modelmapper/jsr310/";
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "TemporalToTemporalConverter.java");
    writeFile(file, "code/modelmapper/jsr310/temporal_to_temporal_converter.ftl", config);

    packagePath = config.getPackageName().replace(".", "/") + "/modelmapper/jsr310/";
    file = new File(projectsRoot + "/src/main/java/" + packagePath, "ToTemporalConverter.java");
    writeFile(file, "code/modelmapper/jsr310/to_temporal_converter.ftl", config);
  }
}
