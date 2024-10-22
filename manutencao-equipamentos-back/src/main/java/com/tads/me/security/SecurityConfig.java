package com.tads.me.security;

import com.tads.me.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public AuthenticationManager authenticationManager() {
        return authentication -> {
            // Dummy authentication logic
            return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials());
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)  // Desativa CSRF para evitar problemas em desenvolvimento
                .authorizeHttpRequests(auth -> auth
                        // Libera todas as rotas relacionadas ao Swagger
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").permitAll()
                        // Permite acesso público ao endpoint de login
                        .requestMatchers("/auth/login").permitAll()
                        // Permite acesso público a outras rotas, se necessário
                        .requestMatchers("/public/**").permitAll()
                        // Qualquer outra rota precisa de autenticação
                        .anyRequest().authenticated()
                )
                .formLogin(AbstractHttpConfigurer::disable)  // Desabilita o formulário de login
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

    // Define um codificador de senhas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
