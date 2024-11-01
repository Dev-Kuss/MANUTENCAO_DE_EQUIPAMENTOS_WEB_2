package com.tads.me.dto;

import com.tads.me.entity.Cliente;
import com.tads.me.entity.Funcionario;

import java.time.LocalDateTime;

public record SolicitacaoRequestDTO(
        LocalDateTime dataHora,
        String descricaoEquipamento,
        String estado,
        Long idCategoria,
        Cliente cliente,
        Funcionario responsavel
) {}
