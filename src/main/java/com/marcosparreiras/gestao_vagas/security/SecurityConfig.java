package com.marcosparreiras.gestao_vagas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

  @Autowired
  private SecurityFilter securityFilter;

  @Autowired
  private SecurityCandidateFilter securityCandidateFilter;

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(auht -> {
        auht
          .requestMatchers("/candidate/")
          .permitAll()
          .requestMatchers("/candidate/profile")
          .permitAll()
          .requestMatchers("/candidate/auth")
          .permitAll()
          .requestMatchers("/company/")
          .permitAll()
          .requestMatchers("/company/auth")
          .permitAll();
        auht.anyRequest().authenticated();
      })
      .addFilterBefore(securityFilter, BasicAuthenticationFilter.class)
      .addFilterBefore(
        securityCandidateFilter,
        BasicAuthenticationFilter.class
      );
    return http.build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
