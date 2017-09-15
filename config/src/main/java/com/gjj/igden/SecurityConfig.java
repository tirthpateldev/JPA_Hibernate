package com.gjj.igden;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  DataSource dataSource;

  @Autowired
  public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication().dataSource(dataSource)
      .usersByUsernameQuery("select account_name ,password, enabled from account where account_name = ?")
      .authoritiesByUsernameQuery("select account_name, role from user_roles where account_name = ?");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    String LOG_IN_URL_PAGE = "/login";
    String LOG_OUT_URL_PAGE = "/logout";
    http.authorizeRequests()
      .antMatchers(LOG_IN_URL_PAGE,
        LOG_OUT_URL_PAGE,
        "/css/**",
        "/js/**",
        "/img/**",
        "/**/favicon.ico",
        "/webjars/**",
        "/login").permitAll()
      .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
      .antMatchers("/admin/update**").access("hasRole('ROLE_ADMIN')")
      .and()
      .formLogin()
      .successHandler(savedRequestAwareAuthenticationSuccessHandler())
      .loginPage("/login")
      .failureUrl("/login?error")
      .loginProcessingUrl("/auth/login_check")
      .usernameParameter("account_name")
      .passwordParameter("password")
      .and()
      .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
      .and()
      .csrf()
      .and()
      .rememberMe().tokenRepository(persistentTokenRepository())
      .tokenValiditySeconds(1209600);
  }

  @Bean
  public PersistentTokenRepository persistentTokenRepository() {
    JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
    db.setDataSource(dataSource);
    return db;
  }

  @Bean
  public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
    SavedRequestAwareAuthenticationSuccessHandler auth =
      new SavedRequestAwareAuthenticationSuccessHandler();
    auth.setTargetUrlParameter("targetUrl");
    return auth;
  }
}