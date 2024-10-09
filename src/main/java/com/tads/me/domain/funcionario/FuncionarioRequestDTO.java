package com.tads.me.domain.funcionario;

import java.time.LocalDate;

public record FuncionarioRequestDTO(
        String email,
        String nome,
        LocalDate dataNascimento,
        String senha
) {
}
