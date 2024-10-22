package com.tads.me.dto;

import java.util.List;

public record LoginResponseDTO(Long id, String email, List<String> roles, String token) {
}
