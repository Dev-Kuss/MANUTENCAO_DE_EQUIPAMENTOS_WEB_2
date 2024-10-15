package com.tads.me.domain.endereco;

public record EnderecoRequestDTO(
        String cep,
        String rua,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado
) {}
