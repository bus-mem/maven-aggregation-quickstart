package ${packageName};

import com.google.common.collect.Lists;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@ServletComponentScan
@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
@EnableScheduling
@EnableCaching
@EnableRetry
<#assign tmpGroupId = groupId?replace("-", "_", "r")>
@MapperScan("${tmpGroupId}.${parentArtifactId}.repository.mapper")
@ComponentScan(value = {"${tmpGroupId}.${parentArtifactId}.repository", "${tmpGroupId}.${parentArtifactId}.api"})
public class ApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(ApiApplication.class, args);
  }

  /**
  * 允许跨域调用的过滤器
  */
  @Bean
  public CorsFilter corsFilter() {
    CorsConfiguration config = new CorsConfiguration();
    //允许跨越发送cookie
    config.setAllowCredentials(true);
    //放行全部原始头信息
    config.addAllowedHeader("*");
    //允许所有请求方法跨域调用
    config.addAllowedMethod("*");
    //允许所有域名进行跨域调用
    config.setAllowedOriginPatterns(Lists.newArrayList("*"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }
}