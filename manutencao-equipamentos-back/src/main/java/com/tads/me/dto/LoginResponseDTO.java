package com.tads.me.dto;

import java.util.List;

public record LoginResponseDTO(java.util.UUID id, String nome, String email, List<String> roles, String token) {
}
