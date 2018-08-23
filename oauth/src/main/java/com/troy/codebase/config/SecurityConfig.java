package com.troy.codebase.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // @formatter:off
    auth.inMemoryAuthentication()
          .withUser("troy")
          .password("pass1")
          .roles("USER")
        .and()
          .withUser("young")
          .password("pass2")
          .roles("USER", "ADMIN");
    // @formatter:on
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // @formatter:off
    http
          .cors()
        .and()
          .csrf()
        .and()
        .authorizeRequests()
          .antMatchers(HttpMethod.OPTIONS).permitAll()
          .anyRequest().authenticated()
        .and()
          .formLogin()
        .and()
          .httpBasic();
    // @formatter:on
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  /**
   * https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#core-services-password-encoding
   * @return
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }
}
