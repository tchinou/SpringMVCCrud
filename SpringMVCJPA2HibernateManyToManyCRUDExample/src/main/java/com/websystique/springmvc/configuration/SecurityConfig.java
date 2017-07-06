package com.websystique.springmvc.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Web MVC Security Java Config Demo Project
 * Configures authentication and authorization for the application.
 * 
 * @author www.codejava.net
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustomSuccessHandler customSuccessHandler;
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("admin").password("nimda").roles("ADMIN");
        auth
        .inMemoryAuthentication()
            .withUser("tchinou").password("123456").roles("USER");
        auth
        .inMemoryAuthentication()
        	.withUser("gaucher").password("123456").roles("USER");
        
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
  
      http.authorizeRequests()
        //.antMatchers("/").permitAll()
      	.antMatchers("/").access("hasRole('USER')")
        .antMatchers("/adminundex**").access("hasRole('ADMIN')")
        .antMatchers("/db**").access("hasRole('USER')")
        .and().formLogin()
        .loginPage("/login").successHandler(customSuccessHandler)
        .usernameParameter("ssoId").passwordParameter("password")
        .and().csrf()
        .and().exceptionHandling().accessDeniedPage("/Access_Denied");
      http.csrf().disable();

    }    
 
}
