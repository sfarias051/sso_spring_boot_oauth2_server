package com.demo.ssos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
          .antMatchers("/login", "/oauth/authorize")
          .and()
          .authorizeRequests()
          .anyRequest().authenticated()
          .and()
          .formLogin().permitAll();
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("praveen")
            .password(passwordEncoder().encode("1234"))
            .roles("USER")
            .and()
            .withUser("tanushree")
            .password(passwordEncoder().encode("1234"))
            .roles("ADMIN");
            
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){ 
        return new BCryptPasswordEncoder(); 
    }
}
