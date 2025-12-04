package com.spring.security.Security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password4j.BcryptPassword4jPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig{


    @Autowired
    private MyUserDetailsService userDetailsService;

    @Bean
    static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

         @Bean
            public SecurityFilterChain securityFilterChain   (HttpSecurity http) throws Exception {



             return http.csrf(AbstractHttpConfigurer::disable)
                     .authorizeHttpRequests(registry -> {
                         registry.requestMatchers("/","/register/add/**").permitAll();
                         registry.requestMatchers("/admin/**").hasRole("ADMIN");
                         registry.requestMatchers("/user/**").hasRole("USER");
                         registry.anyRequest().authenticated();

                     }).formLogin(Customizer.withDefaults())
                     .httpBasic(Customizer.withDefaults())
                     .build();
         }
    @Bean
    public UserDetailsService userDetailsService(){
        return userDetailsService;
    }


    @Bean
    AuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider provider= new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
       // provider.setUserDetailsService(userDetailsService);
        return  provider;
    }

    @Bean
    public AuthenticationManager authenticationManager
            (AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return  authenticationConfiguration.getAuthenticationManager();
    }



}













