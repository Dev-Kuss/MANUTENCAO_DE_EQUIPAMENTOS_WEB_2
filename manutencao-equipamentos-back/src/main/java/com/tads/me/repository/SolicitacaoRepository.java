package com.tads.me.repository;

import com.tads.me.entity.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {
    List<Solicitacao> findByClienteId(UUID cliente_id);

    default List<Solicitacao> findAllByOrderByIdDesc() {
        return findAll(Sort.by(Sort.Direction.DESC, "idSolicitacao"));
    }
}
