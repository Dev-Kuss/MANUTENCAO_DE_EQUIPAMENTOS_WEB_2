package com.tads.me.dto;

import java.time.LocalDate;

public record FuncionarioRequestDTO(
        String email,
        String nome,
        String telefone,
        LocalDate dataNascimento,
        String senha
) {
}
