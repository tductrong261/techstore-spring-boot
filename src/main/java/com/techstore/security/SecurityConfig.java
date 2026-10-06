package com.techstore.security;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.authorizeHttpRequests(
            auth ->
                auth.dispatcherTypeMatchers(DispatcherType.ERROR, DispatcherType.FORWARD)
                    .permitAll()
                    .requestMatchers(
                        "/",
                        "/products",
                        "/products/**",
                        "/error",
                        "/css/**",
                        "/js/**",
                        "/images/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
        .csrf(csrf -> csrf.disable());

    return http.build();
  }
}
