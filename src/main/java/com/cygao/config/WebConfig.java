package com.cygao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author cygao
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

  /**
   * 将/login路由映射到login.html模板
   * 将/home路由映射到home.html模板
   */
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/login").setViewName("login");
    registry.addViewController("/home").setViewName("home");
  }

  /**
   * 注入{@code passwordEncoder}用于密码加密,使用BCrypt加密算法
   * @return BCryptPasswordEncoder
   */
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
