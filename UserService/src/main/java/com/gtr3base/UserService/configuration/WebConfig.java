package com.gtr3base.UserService.configuration;

import com.gtr3base.UserService.details.UserDetailsImpl;
import com.gtr3base.UserService.details.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author hypad on 22.04.2025
 * @project SMedia
 */
@Configuration
@EnableWebSecurity
public class WebConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req.requestMatchers("/login", "/users", "/register")
                        .permitAll()
                        .anyRequest().authenticated())
                .userDetailsService(userDetailsService())
                .formLogin(login -> login.loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .usernameParameter("email")
                        .permitAll())
                .logout(LogoutConfigurer::permitAll);
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
