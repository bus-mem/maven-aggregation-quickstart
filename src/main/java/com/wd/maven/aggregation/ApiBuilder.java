package com.wd.maven.aggregation;


/**
 * @author solom
 * @ClassName WebBuilder
 * @Description
 * @Version 1.0.0
 * @create 2022-02-24 14:07
 */
public class ApiBuilder extends BaseBuilder {

  private LogbackBuilderImpl logbackBuilder = new LogbackBuilderImpl();
  private CommonBuilderImpl commonBuilder = new CommonBuilderImpl();
  private ConfigBuilderImpl configBuilder = new ConfigBuilderImpl();
  private PropertiesBuilderImpl propertiesBuilder = new PropertiesBuilderImpl();
  private ApplicationBuilderImpl applicationBuilder = new ApplicationBuilderImpl();
  private PomBuilderImpl pomBuilder = new PomBuilderImpl();
  private PackageBuilder packageBuilder = new PackageBuilder();

  public void generation(PropertiesConfig config, String projectsRoot) throws Exception {
    PropertiesConfig webConfig = new PropertiesConfig();
    webConfig.setConfig(config);
    webConfig.setDatasource(config.getDatasource());
    webConfig.setRedis(config.getRedis());
    webConfig.setArtifactId("api");
    webConfig.setDescription("api");
    webConfig.setPackageName((config.getGroupId() + "." + config.getArtifactId() + ".api").replace("-", "_"));
    webConfig.setGroupId(config.getGroupId());
    webConfig.setAutoExecuteSQL(config.isAutoExecuteSQL());
    webConfig.setParentArtifactId(config.getParentArtifactId());
    webConfig.setParentArtifactId(config.getParentArtifactId());

    applicationBuilder.generation(webConfig, projectsRoot);
    pomBuilder.generation(webConfig, projectsRoot, "pom_web.ftl");
    propertiesBuilder.generation(webConfig, projectsRoot);
    logbackBuilder.generation(webConfig, projectsRoot);
    commonBuilder.generation(webConfig, projectsRoot);
    configBuilder.generation(webConfig, projectsRoot);
    packageBuilder.generation(webConfig, projectsRoot);
  }
}
