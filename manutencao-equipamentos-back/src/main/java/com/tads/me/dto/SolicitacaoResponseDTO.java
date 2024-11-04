package com.tads.me.dto;

import com.tads.me.entity.CategoriaEquipamento;
import com.tads.me.entity.Cliente;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tads.me.entity.Funcionario;
import com.tads.me.entity.Solicitacao;
import com.tads.me.entity.HistoricoSolicitacao;
import com.tads.me.entity.Orcamento;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public record SolicitacaoResponseDTO(
        Long id,
        LocalDateTime dataHora,
        String descricaoEquipamento,
        String descricaoDefeito,
        String estado,
        LocalDateTime dataPagamento,
        LocalDateTime dataHoraFinalizacao,
        CategoriaEquipamento categoria,
        Cliente cliente,
        Funcionario responsavel,
        List<HistoricoSolicitacao> historico,
        List<Orcamento> orcamentos
) {
    public SolicitacaoResponseDTO(Solicitacao solicitacao) {
        this(
                solicitacao.getIdSolicitacao(),
                solicitacao.getDataHora(),
                solicitacao.getDescricaoEquipamento(),
                solicitacao.getDescricaoDefeito(),
                solicitacao.getEstado(),
                solicitacao.getDataPagamento(),
                solicitacao.getDataHoraFinalizacao(),
                solicitacao.getCategoria(),
                solicitacao.getCliente(),
                solicitacao.getResponsavel(),
                solicitacao.getHistorico(),
                solicitacao.getOrcamentos()
        );
    }
}