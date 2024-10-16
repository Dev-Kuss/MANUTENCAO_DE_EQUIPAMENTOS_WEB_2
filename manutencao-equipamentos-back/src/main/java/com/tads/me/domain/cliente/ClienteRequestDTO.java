package com.tads.me.domain.cliente;

public record ClienteRequestDTO(
        String nome,
        String cpf,
        String email,
        String telefone,
        String senha
) {
}
