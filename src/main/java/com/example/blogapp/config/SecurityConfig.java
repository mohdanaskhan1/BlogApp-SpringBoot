package com.example.blogapp.config;

import com.example.blogapp.repository.UserRegisterEntityRepository;
import com.example.blogapp.security.JWTAuthenticationFilter;
import com.example.blogapp.security.JWTRefreshFilter;
import com.example.blogapp.security.JWTUtil;
import com.example.blogapp.security.JWTValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JWTUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final UserRegisterEntityRepository userRepo;

    public SecurityConfig(JWTUtil jwtUtil, UserDetailsService userDetailsService, UserRegisterEntityRepository userRepo) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.userRepo = userRepo;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        //Authentication filter responsible for login
        JWTAuthenticationFilter jwtAuthFilter = new JWTAuthenticationFilter(authenticationManager, this.jwtUtil);

        //Validation filter for checking JWT in every request
        JWTValidationFilter jwtValidationFilter = new JWTValidationFilter(this.jwtUtil, userDetailsService);

        //refresh filter for checking JWT in every request
        JWTRefreshFilter jwtRefreshFilter = new JWTRefreshFilter(this.jwtUtil, userRepo);


        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/user-register", "/generate-token","/refresh-token").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) // Generate Token Filter
                .addFilterAfter(jwtValidationFilter, JWTAuthenticationFilter.class) // Validate Token Filter
                .addFilterAfter(jwtRefreshFilter, JWTValidationFilter.class); //refresh token filter

        //Auth -> Validation -> UserName
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }


//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user1 = User.withUsername("username1")
//                .password("{noop}pass1")
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user2 = User.withUsername("username2")
//                .password("{bcrypt}"+new BCryptPasswordEncoder().encode("pass2"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user3 = User.withUsername("username2")
//                .password(new BCryptPasswordEncoder().encode("pass2"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user2,user1,user3);
//    }

}
