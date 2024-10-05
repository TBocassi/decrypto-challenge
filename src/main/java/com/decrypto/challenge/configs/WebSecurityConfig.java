package com.decrypto.challenge.configs;

import com.decrypto.challenge.security.components.JwtRequestFilter;
import com.decrypto.challenge.security.services.impl.CustomUserDetailsServiceImpl;
import com.decrypto.challenge.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Lazy
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/authenticate","/users/register").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-resources/**", "/swagger-ui/**", "/webjars/**").permitAll()
                        .requestMatchers("/client/stats").hasAuthority(Constants.USER_ROLE_NAME)
                        .anyRequest().hasAuthority(Constants.ADMIN_ROLE_NAME)
                        .and()
                        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        return authenticationManagerBuilder.userDetailsService(customUserDetailsService()).passwordEncoder(passwordEncoder()).and().build();
    }

    @Bean
    public CustomUserDetailsServiceImpl customUserDetailsService() { // Cambiado a camelCase
        return new CustomUserDetailsServiceImpl();
    }
}


