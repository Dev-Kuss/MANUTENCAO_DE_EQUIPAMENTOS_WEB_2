package com.tads.me.security;

import com.tads.me.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class PasswordEncoderConfig {

    @Bean
    public SHA256PasswordEncoder sha256PasswordEncoder() {
        return new SHA256PasswordEncoder();
    }
}

