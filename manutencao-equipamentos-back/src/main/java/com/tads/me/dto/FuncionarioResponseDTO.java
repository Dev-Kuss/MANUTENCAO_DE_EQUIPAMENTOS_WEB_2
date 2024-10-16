package com.tads.me.dto;

import com.tads.me.entity.Funcionario;

public record FuncionarioResponseDTO(
        Long id,
        String email,
        String nome,
        String dataNascimento
) {
    public FuncionarioResponseDTO(Funcionario funcionario) {
        this(
                funcionario.getId(),
                funcionario.getEmail(),
                funcionario.getNome(),
                funcionario.getDataNascimento().toString()
        );
    }
}
