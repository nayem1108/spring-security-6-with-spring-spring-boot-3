package com.nayem.springsecuritypractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {


    @Bean
    UserDetailsManager userDetailsManager(){
        UserDetails nayem = User.builder().username("nayem").password(passwordEncoder().encode("123nayem")).roles("USER").build();
        UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(nayem, admin);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf ->csrf.disable())
        .authorizeHttpRequests(auth -> 
        auth.anyRequest().authenticated())
        .httpBasic(Customizer.withDefaults())
        // this helps to show form view in login 
        .formLogin(Customizer.withDefaults());
        
        return http.build();
    }
    


    @Bean 
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
