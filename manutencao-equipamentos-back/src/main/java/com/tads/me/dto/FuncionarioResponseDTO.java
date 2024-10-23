package com.tads.me.dto;

import com.tads.me.entity.Funcionario;

import java.util.UUID;

public record FuncionarioResponseDTO(
        UUID id,
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
