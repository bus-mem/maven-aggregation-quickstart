package ${packageName};

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import java.util.Collections;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;

/**
* @author: solom
* @Version 1.0.0
* @create ${.now?string["yyyy-MM-dd HH:mm:ss"]}
* @since: jdk 1.8
*/
public class CodeGenerator {

  /**
   * <p>
   * 读取控制台内容
   * </p>
   */
  public static String scanner(String tip) {
    Scanner scanner = new Scanner(System.in);
    StringBuilder help = new StringBuilder();
    help.append("请输入" + tip + "：");
    System.out.println(help.toString());
    if (scanner.hasNext()) {
      String ipt = scanner.next();
      if (StringUtils.isNotEmpty(ipt)) {
        return ipt;
      }
    }
    throw new MybatisPlusException("请输入正确的" + tip + "！");
  }

  public static void main(String[] args) {
    String projectPath = System.getProperty("user.dir");
    FastAutoGenerator.create(
            "${datasource.mysql.url}",
            "${datasource.mysql.username}", "${datasource.mysql.password}")
        .templateConfig(builder -> {
          builder.disable(TemplateType.CONTROLLER);
          builder.serviceImpl("serviceImpl.java");
          builder.entity("entity.java");
        })
        .globalConfig(builder -> {
          builder.author("Solom")        // 设置作者
              .enableSwagger()        // 开启 swagger 模式 默认值:false
              .enableSpringdoc()
              .disableOpenDir()
              .dateType(
                  DateType.TIME_PACK)   //定义生成的实体类中日期类型 DateType.ONLY_DATE 默认值: DateType.TIME_PACK
              .outputDir(projectPath + "/src/main/java"); // 指定输出目录
        })
        .packageConfig(builder -> {
          builder.parent("${packageName}") // 父包模块名
              .entity("entity")           //Entity 包名 默认值:entity
              .service("service")         //Service 包名 默认值:service
              .mapper("mapper")           //Mapper 包名 默认值:mapper
              .pathInfo(Collections.singletonMap(OutputFile.xml,
                  projectPath + "/src/main/resources/mapper")); // 设置mapperXml生成路径
          //默认存放在mapper的xml下
        })
        .strategyConfig(builder -> {
          builder.addInclude(
                  scanner("表名，多个英文逗号分割").split(
                      ",")) // 设置需要生成的表名 可边长参数“user”, “user1”
              .serviceBuilder()//service策略配置
              .enableFileOverride()
              .formatServiceFileName("I%sService")
              .formatServiceImplFileName("%sServiceImpl")
              .serviceImplTemplate("serviceImpl.java")
              .entityBuilder()// 实体类策略配置
              .idType(IdType.AUTO)//主键策略  雪花算法自动生成的id
              .enableFileOverride()
              .enableLombok() //开启lombok
              .enableTableFieldAnnotation()// 属性加上注解说明
              .naming(NamingStrategy.underline_to_camel)
              .columnNaming(NamingStrategy.underline_to_camel)
              .superClass("${packageName}.entity.convert.Convert")
              .controllerBuilder().disable() //controller 策略配置
              .enableFileOverride()
              .formatFileName("%sController")
              .mapperBuilder()// mapper策略配置
              .enableFileOverride()
              .enableBaseResultMap()
              .enableBaseColumnList()
              .formatMapperFileName("%sMapper")
              .enableMapperAnnotation()//@mapper注解开启
              .formatXmlFileName("%sMapper");
        })
        // 使用Freemarker引擎模板，默认的是Velocity引擎模板
        //.templateEngine(new FreemarkerTemplateEngine())
        .templateEngine(new FreemarkerTemplateEngine())
        .execute();
  }
}