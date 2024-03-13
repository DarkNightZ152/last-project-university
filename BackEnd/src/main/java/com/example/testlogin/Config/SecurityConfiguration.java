package com.example.testlogin.Config;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http)
    throws Exception {
    http
      .csrf(AbstractHttpConfigurer::disable)
      .cors(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests(authorize ->
        authorize
          // .requestMatchers("/api/v1/auth/**")
          // .permitAll()
          // .requestMatchers("/api/v1/shop/**")
          // .permitAll()
          // .requestMatchers("/api/v1/product/**")
          // .permitAll()
          // .requestMatchers("/api/v1/image/**")
          // .permitAll()
          // .requestMatchers("/api/v1/admin/**")
          // .hasAuthority("ADMIN")
          // .requestMatchers("/api/v1/staff/**")
          // .hasAnyAuthority("STAFF", "ADMIN")
          // .requestMatchers("/api/v1/user/**")
          // .hasAuthority("CUSTOMER")
          .anyRequest()
          .permitAll()
      // .authenticated()
      )
      .sessionManagement(sess ->
        sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      )
      .authenticationProvider(authenticationProvider)
      .addFilterBefore(
        jwtAuthFilter,
        UsernamePasswordAuthenticationFilter.class
      );

    return http.build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("*"));
    configuration.setAllowedMethods(
      Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
    );
    configuration.setAllowCredentials(true);
    configuration.setAllowedHeaders(
      Arrays.asList("Authorization", "Cache-Control", "Content-Type")
    );
    UrlBasedCorsConfigurationSource source =
      new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
