package com.troy.codebase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class OAuthConfig extends ResourceServerConfigurerAdapter {

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
        .cors().disable()
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
          .antMatchers(HttpMethod.OPTIONS)
          .permitAll()
        .and()
          .authorizeRequests()
          .anyRequest()
          .authenticated();
  }

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources.tokenStore(tokenStore());
    resources.tokenServices(remoteTokenServices());
  }

  @Bean
  public TokenStore tokenStore() {
    return new JwtTokenStore(accessTokenConverter());
  }

  @Bean
  public JwtAccessTokenConverter accessTokenConverter() {
    return new JwtAccessTokenConverter();
  }

  @Primary
  @Bean
  public RemoteTokenServices remoteTokenServices() {
    final RemoteTokenServices tokenServices = new RemoteTokenServices();

    tokenServices.setCheckTokenEndpointUrl("http://localhost:8081/oauth/check_token");
    tokenServices.setClientId("trusted-app");
    tokenServices.setClientSecret("secret");

    return tokenServices;
  }
}
