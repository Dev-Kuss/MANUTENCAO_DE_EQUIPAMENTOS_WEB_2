package com.tads.me.dto;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tads.me.entity.Solicitacao;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public record SolicitacaoResponseDTO(
        Long idSolicitacao,
        String descricaoEquipamento,
        String descricaoDefeito,
        LocalDateTime dataHora,
        LocalDateTime dataHoraFinalizacao,
        LocalDateTime dataPagamento,
        String estado,
        String clienteNome,
        String categoriaNome,
        String responsavelNome,
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
                solicitacao.getCliente().getNome(),
                solicitacao.getCategoria().getNome_categoria(),
                solicitacao.getResponsavel().getNome(),
                solicitacao.getHistoricos().stream().map(HistoricoSolicitacaoResponseDTO::new).collect(Collectors.toList()),
                solicitacao.getOrcamentos().stream().map(OrcamentoResponseDTO::new).collect(Collectors.toList())
        );
    }
}