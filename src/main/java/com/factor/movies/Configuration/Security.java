package com.factor.movies.Configuration;

import com.factor.movies.Model.Roles;
import com.factor.movies.Service.AdminLogic;
import com.factor.movies.Service.UserLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@EnableWebSecurity
@Configuration


public class Security {
    @Bean
    public UserDetailsService userDetailsService(AdminLogic adminLogic, UserLogic userLogic) {
        return username -> {
            try {
                return adminLogic.loadUserByUsername(username);
            } catch (UsernameNotFoundException e) {
                return userLogic.loadUserByUsername(username);
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                          .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/upload").hasRole("ADMIN")
                        .requestMatchers("/register/admin/update").hasRole("ADMIN")
                        .requestMatchers("/register/admin").permitAll()
                        .requestMatchers("/register/user").permitAll()
                        .requestMatchers("/account/delete").hasRole("USER")
                        .requestMatchers("/register/user/update").hasRole("USER")
                        .anyRequest().authenticated())

                        .httpBasic(Customizer.withDefaults())
                        .build();
    }
}
