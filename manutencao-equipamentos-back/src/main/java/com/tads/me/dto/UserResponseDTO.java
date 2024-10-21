package com.tads.me.dto;

import com.tads.me.entity.User;
import java.util.Set;

public record UserResponseDTO(
        Long id,
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
