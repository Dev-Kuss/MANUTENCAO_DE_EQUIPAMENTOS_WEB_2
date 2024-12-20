package com.tads.me.dto;

public record ClienteRequestDTO(
        String nome,
        String cpf,
        String email,
        String telefone,
        String senha,
        String cep,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado
) {
}
