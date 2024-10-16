package com.tads.me.dto;

import com.tads.me.entity.Endereco;

public record EnderecoResponseDTO(
        Long id,
        String cep,
        String rua,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado
) {
    public EnderecoResponseDTO(Endereco endereco) {
        this(
                endereco.getId(),
                endereco.getCep(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado()
        );
    }
}
