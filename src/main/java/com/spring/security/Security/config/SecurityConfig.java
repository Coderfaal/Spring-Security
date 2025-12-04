package com.spring.security.Security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

@Bean
public SecurityFilterChain securityFilterChain   (HttpSecurity http) throws Exception  {

    //http.authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll());
    http.csrf(customizer -> customizer.disable());
  //  http.authorizeHttpRequests(request-> request.anyRequest().authenticated());
   // http.authorizeHttpRequests((authorize)->authorize.requestMatchers("/**").hasAnyRole("USER","ADMIN"));
    http.authorizeHttpRequests((authorize)
            ->authorize.requestMatchers("/root/inbox").hasRole("USER"));

    http.authorizeHttpRequests((authorize)
            ->authorize.requestMatchers("/root/adminOffice").hasRole("ADMIN"));
    http.authorizeHttpRequests((authorize)
            ->authorize.requestMatchers("/root/all").permitAll());
    http.authorizeHttpRequests((authorize)
            ->authorize.requestMatchers("/root/useradmin").hasAnyRole("USER","ADMIN"));

   http.formLogin(Customizer.withDefaults());  // follows form login apporach


    http.httpBasic(Customizer.withDefaults());
    http.sessionManagement(session
            -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));

    return http.build();
}
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("faal")
                .password("faal098")
                .roles("USER")
                .build();

        UserDetails user2 = User.withDefaultPasswordEncoder()
                                    // attach the roles to authorization, dont forget , roles are not compulsory, if you dont  validate the role it will still go through
                .username("Anya")
                .password("bond")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1 ,user2); // this user deatils will be saved in the memory
    }










}



// attach the roles to authorization, dont forget , roles are not compulsory,
// if you dont  validate the role it will still go through
//make sure u logout and relogin or change server port
// hasRole("USER")//here roles should be validated like either user or admin, both should be admin or user, one user one admin cannot