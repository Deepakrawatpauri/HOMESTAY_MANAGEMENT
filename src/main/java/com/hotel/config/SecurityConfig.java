 package com.hotel.config;




 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.security.authentication.AuthenticationManager;
 import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
 import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
 import org.springframework.security.config.annotation.web.builders.HttpSecurity;
 import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
 import org.springframework.security.core.userdetails.UserDetailsService;
 import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 import org.springframework.security.crypto.password.PasswordEncoder;
 import org.springframework.security.web.SecurityFilterChain;

 @Configuration
 @EnableWebSecurity
 public class SecurityConfig {

   

     @Bean
     public UserDetailsService getuserDetailsService() {
         return new UserDetailsServicesImpl();
     }

     @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http.authorizeHttpRequests(authorize -> authorize
                 .requestMatchers("/admin/**").hasRole("ADMIN")
                 .requestMatchers("/user/**").hasRole("USER")
                 .requestMatchers("/**").permitAll()
             )
             .formLogin(form -> form
                 .loginPage("/login")
                 .loginProcessingUrl("/login")
                 .defaultSuccessUrl("/user/home")
                 .permitAll()
             )
             .csrf(csrf -> csrf.disable());

         return http.build();
     }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
         return new BCryptPasswordEncoder();
     }

     @Bean
     public DaoAuthenticationProvider daoAuthenticationProvider() {
         DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
         authProvider.setUserDetailsService(getuserDetailsService());
         authProvider.setPasswordEncoder(getPasswordEncoder());
         return authProvider;
     }

     @Bean
     public AuthenticationManager authManager(HttpSecurity http) throws Exception {
         return http.getSharedObject(AuthenticationManagerBuilder.class)
                    .authenticationProvider(daoAuthenticationProvider())
                    .build();
     }
 }
