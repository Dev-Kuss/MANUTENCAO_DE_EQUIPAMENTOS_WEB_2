package com.tads.me.dto;

public record ClienteRequestDTO(
        String nome,
        String cpf,
        String email,
        String telefone,
        String senha
) {
}
