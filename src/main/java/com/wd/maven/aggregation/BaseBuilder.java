package com.wd.maven.aggregation;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;

/*
 * ProjectName: maven-aggregation
 * PackageName: com.wd.maven.aggregation
 * Description:
 * CreateBy: Solom
 * Email: solomo.kin@gmail.com
 * Copyright: Solom
 * CreatedTime: 2026-01-06 09:48:09:48
 */
public class BaseBuilder {

  protected Template getTemplate(String ftl) throws IOException {
    Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
    cfg.setClassForTemplateLoading(getClass(), "/templates");
    cfg.setDefaultEncoding("UTF-8");
    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    return cfg.getTemplate(ftl);
  }

  protected void writeFile(File file, String ftl, Object dataModel) throws IOException, TemplateException {
    if (!file.exists()) {
      if (!file.getParentFile().exists()) {
        file.getParentFile().mkdirs();
      }
      file.createNewFile();
    }
    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file));
    try {
      getTemplate(ftl).process(dataModel, outputStreamWriter);
    } finally {
      outputStreamWriter.flush();
      outputStreamWriter.close();
    }
  }
}
