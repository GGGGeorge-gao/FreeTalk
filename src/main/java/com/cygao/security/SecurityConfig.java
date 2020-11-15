package com.cygao.security;

import com.cygao.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security配置
 * @author cygao
 */
@EnableAutoConfiguration
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserServiceImpl userService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
              .antMatchers("/", "/home").access("hasRole('ROLE_ORDINARY')")
              .antMatchers("/logout", "/api/register").permitAll()
            .and()
              .cors()
            .and()
              .formLogin().loginPage("/login")
            .and()
              .logout().permitAll().logoutSuccessUrl("/login")
            .and()
              .csrf().disable();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/register");
  }
}
