package com.wd.maven.aggregation;

/**
 * @author solom
 * @ClassName ProjectConfig
 * @Description 配置文件的配置
 * @Version 1.0.0
 * @create 2022-02-17 16:04
 */
public class PropertiesConfig implements Cloneable{

  public static class Datasource implements Cloneable{
    private Mysql mysql;

    public Mysql getMysql() {
      return mysql;
    }

    public void setMysql(Mysql mysql) {
      this.mysql = mysql;
    }
  }

  public static class Mysql implements Cloneable{
    private String url;
    private String username;
    private String password;

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }
  }

  private Datasource datasource;

  public static class Redis implements Cloneable{
    private String host;
    private String password;
    private String port;
    private String database;

    public String getHost() {
      return host;
    }

    public void setHost(String host) {
      this.host = host;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }

    public String getPort() {
      return port;
    }

    public void setPort(String port) {
      this.port = port;
    }

    public String getDatabase() {
      return database;
    }

    public void setDatabase(String database) {
      this.database = database;
    }
  }

  private Redis redis;
  private String applicationJavaName;
  private String description;
	private String groupId;
	private String artifactId;
  private String parentArtifactId;

  public String getParentArtifactId() {
    return parentArtifactId;
  }

  public void setParentArtifactId(String parentArtifactId) {
    this.parentArtifactId = parentArtifactId;
  }

  private String packageName;
	private PropertiesConfig config;
	private boolean autoExecuteSQL;

  public Datasource getDatasource() {
    return datasource;
  }

  public void setDatasource(Datasource datasource) {
    this.datasource = datasource;
  }

  public Redis getRedis() {
    return redis;
  }

  public void setRedis(Redis redis) {
    this.redis = redis;
  }

  public String getApplicationJavaName() {
    return applicationJavaName;
  }

  public void setApplicationJavaName(String applicationJavaName) {
    this.applicationJavaName = applicationJavaName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public String getArtifactId() {
    return artifactId;
  }

  public void setArtifactId(String artifactId) {
    this.artifactId = artifactId;
  }

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public PropertiesConfig getConfig() {
    return config;
  }

  public void setConfig(PropertiesConfig config) {
    this.config = config;
  }

  public boolean isAutoExecuteSQL() {
    return autoExecuteSQL;
  }

  public void setAutoExecuteSQL(boolean autoExecuteSQL) {
    this.autoExecuteSQL = autoExecuteSQL;
  }
}
