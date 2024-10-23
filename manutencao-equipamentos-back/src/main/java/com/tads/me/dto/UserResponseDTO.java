package com.tads.me.dto;

import com.tads.me.entity.User;
import java.util.Set;
import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String email,
        Set<String> roles
) {
    public UserResponseDTO(User user) {
        this(
                user.getId(),
                user.getEmail(),
                user.getRoles()
        );
    }
}
