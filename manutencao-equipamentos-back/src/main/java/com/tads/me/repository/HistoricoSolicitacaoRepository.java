package com.tads.me.repository;

import com.tads.me.entity.HistoricoSolicitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoSolicitacaoRepository extends JpaRepository<HistoricoSolicitacao, Long> {
    List<HistoricoSolicitacao> findBySolicitacaoIdSolicitacaoOrderByDataHoraDesc(Long idSolicitacao);
}
