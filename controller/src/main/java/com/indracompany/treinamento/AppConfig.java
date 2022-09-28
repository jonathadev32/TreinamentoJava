package com.indracompany.treinamento;

import java.util.concurrent.TimeUnit;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.common.cache.CacheBuilder;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableCaching
@EnableAsync
@EnableScheduling
@EnableSwagger2
@EnableWebMvc
@PropertySource(value = "classpath:application.yml")
@ComponentScan(basePackages = {"io.swagger", "io.swagger.api", "io.swagger.configuration", "com.indracompany.treinamento"})
@ServletComponentScan
public class AppConfig extends WebSecurityConfigurerAdapter{

  public static void main(final String[] args) {
    SpringApplication.run(AppConfig.class, args);
  }

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  @Bean
  public CacheManager cacheManager() {
    return new ConcurrentMapCacheManager() {
      @Override
      protected Cache createConcurrentMapCache(final String name) {
        return new ConcurrentMapCache(name, CacheBuilder.newBuilder().expireAfterWrite(8, TimeUnit.HOURS).maximumSize(10000).build().asMap(), false);
      }
    };
  }
  
  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
  
  @Bean(BeanIds.AUTHENTICATION_MANAGER)
  public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
  }
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
      http
              .cors()
              .and()
              .csrf().disable()
              .exceptionHandling()
              .and()
              .headers().frameOptions().sameOrigin();

  }

  public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBean() {
    return new PersistenceAnnotationBeanPostProcessor();
  }
}
