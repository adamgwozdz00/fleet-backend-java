package pl.ag.fleet.manager.security;

import java.util.Arrays;
import java.util.Collections;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private JwtRequestFilter jwtRequestFilter;
  @Value("${allowedOrigin}")
  private String allowedOrigin;

  @Bean
  public PasswordEncoder getBcryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        .csrf().disable()
        .cors().configurationSource(corsConfigurationSource())
        .and()
        .authorizeRequests()
        .antMatchers("/login", "/swagger-ui/**", "/v2/api-docs", "/configuration/ui",
            "/swagger-resources/**", "/configuration/**", "/swagger-ui.html"
            , "/webjars/**", "/csrf", "/").permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }



  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    val corsConfiguration = new CorsConfiguration();
    corsConfiguration.setAllowedOrigins(Collections.singletonList(allowedOrigin));
    corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
    corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
    corsConfiguration.setExposedHeaders(Collections.singletonList("*"));
    val source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfiguration);
    return source;
  }

//  @Bean
//  public WebMvcConfigurer corsConfigurer() {
//    return new WebMvcConfigurer() {
//      @Override
//      public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//            .allowedOrigins(allowedOrigin)
//            .allowedHeaders("*")
//            .allowedMethods("*");
//      }
//    };
//  }

}
