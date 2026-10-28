package com.factor.movies.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class Security {
    private final JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                          .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/upload_movie").hasRole("ADMIN")
                        .requestMatchers("/register/admin/update_data").hasRole("ADMIN")
                        .requestMatchers("/delete/*").hasRole("ADMIN")
                        .requestMatchers("/update/{detail}").hasRole("ADMIN")
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/register/admin").permitAll()
                        .requestMatchers("/register/user").permitAll()
                        .requestMatchers("/account/delete").hasRole("USER")
                        .requestMatchers("/register/user/update").hasRole("USER")
                        .requestMatchers("/v3/api-docs",
                                         "/v3/api-docs/**",
                                          "/swagger-ui/**",
                                          "/swagger-ui.html",
                                          "/swagger-resources/**",
                                          "/webjars/**").permitAll()
                        .anyRequest()
                        .authenticated())

                        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
