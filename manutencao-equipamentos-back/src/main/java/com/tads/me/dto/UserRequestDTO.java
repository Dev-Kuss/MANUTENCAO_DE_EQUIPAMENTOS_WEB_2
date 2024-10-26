package com.tads.me.dto;

import java.util.Set;

public record UserRequestDTO(
        String email,
        String passwordHash,
        Set<String> roles // Exemplo: ADMIN, USER
) {
}
