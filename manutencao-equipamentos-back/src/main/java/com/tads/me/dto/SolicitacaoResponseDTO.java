package com.tads.me.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tads.me.entity.Solicitacao;
import lombok.Builder;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
public record SolicitacaoResponseDTO(
        Long idSolicitacao,
        String descricaoEquipamento,
        String descricaoDefeito,
        LocalDateTime dataHora,
        LocalDateTime dataHoraFinalizacao,
        LocalDateTime dataPagamento,
        String estado,
        CategoriaEquipamentoResponseDTO categoria,
        ClienteResponseDTO cliente,
        List<HistoricoSolicitacaoResponseDTO> historicos,
        List<OrcamentoResponseDTO> orcamentos
) {
    public SolicitacaoResponseDTO(Solicitacao solicitacao) {
        this(
                solicitacao.getIdSolicitacao(),
                solicitacao.getDescricaoEquipamento(),
                solicitacao.getDescricaoDefeito(),
                solicitacao.getDataHora(),
                solicitacao.getDataHoraFinalizacao(),
                solicitacao.getDataPagamento(),
                solicitacao.getEstado(),
                new CategoriaEquipamentoResponseDTO(solicitacao.getCategoria()), // Usando CategoriaEquipamentoResponseDTO
                new ClienteResponseDTO(solicitacao.getCliente()),
                solicitacao.getHistoricos().stream().map(HistoricoSolicitacaoResponseDTO::new).collect(Collectors.toList()),
                solicitacao.getOrcamentos().stream().map(OrcamentoResponseDTO::new).collect(Collectors.toList())
        );
 
    }
}