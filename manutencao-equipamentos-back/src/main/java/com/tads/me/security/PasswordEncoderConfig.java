package com.tads.me.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PasswordEncoderConfig {

    @Bean
    public SHA256PasswordEncoder sha256PasswordEncoder() {
        return new SHA256PasswordEncoder();
    }
}

