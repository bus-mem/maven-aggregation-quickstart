package ${packageName};

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author solom
 * @ClassName RedisConfig
 * @Description
 * @Version 1.0.0
 * @create ${.now?string["yyyy-MM-dd HH:mm:ss"]}
 * @since: jdk 17
 */
@Configuration
public class RedisConfig {

  /**
  * Connection factory redis connection factory.
  *
  * @param hostName the host name
  * @param port     the port
  * @param password the password
  * @param index    the index
  * @return the redis connection factory
  */
  public RedisConnectionFactory connectionFactory(String hostName, int port, String password, int index) {
    //单机版jedis
    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
    //设置redis服务器的host或者ip地址
    redisStandaloneConfiguration.setHostName(hostName);
    //设置密码
    redisStandaloneConfiguration.setPassword(password);
    //设置redis的服务的端口号
    redisStandaloneConfiguration.setPort(port);
    //设置默认使用的数据库
    redisStandaloneConfiguration.setDatabase(index);
    //获得默认的连接池构造器
    LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder().poolConfig(redisPoolConfig()).build();
    return new LettuceConnectionFactory(redisStandaloneConfiguration, clientConfiguration);
  }

  @Bean
  @Primary
  @ConfigurationProperties(prefix = "spring.redis.lettuce.pool")
    public GenericObjectPoolConfig redisPoolConfig() {
    return new GenericObjectPoolConfig();
  }

  @Bean(name = "redisConnectionFactory")
  @ConditionalOnMissingBean(name = "redisConnectionFactory")
  public RedisConnectionFactory redisConnectionFactory(
    @Value("${r'${spring.data.redis.host}'}") String hostName, @Value("${r'${spring.data.redis.port}'}") int port,
    @Value("${r'${spring.data.redis.password}'}") String password,
    @Value("${r'${spring.data.redis.database}'}") int index) {
    return connectionFactory(hostName, port, password, index);
  }

  @Bean
  @ConditionalOnMissingBean
  public StringRedisTemplate redisTemplate(@Qualifier("redisConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
    StringRedisTemplate template = new StringRedisTemplate();
    GsonRedisSerializer serializer = new GsonRedisSerializer();
    template.setValueSerializer(serializer);
    template.setHashValueSerializer(serializer);
    template.setKeySerializer(new StringRedisSerializer());
    template.setHashKeySerializer(new StringRedisSerializer());
    template.setConnectionFactory(redisConnectionFactory);
    template.afterPropertiesSet();
    return template;
  }
}