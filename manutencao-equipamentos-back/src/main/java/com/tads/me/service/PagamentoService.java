package com.tads.me.service;

import com.tads.me.domain.pagamento.Pagamento;
import com.tads.me.domain.pagamento.PagamentoRequestDTO;
import com.tads.me.domain.pagamento.PagamentoResponseDTO;
import com.tads.me.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    @Transactional
    public Pagamento createPagamento(PagamentoRequestDTO data) {
        Pagamento pagamento = Pagamento.builder()
                .valor(data.valor())
                .dataHoraPagamento(data.dataHoraPagamento())
                .solicitacao(null)  // Atribuir corretamente a solicitação
                .build();
        repository.save(pagamento);
        return pagamento;
    }

    public List<PagamentoResponseDTO> listarPagamentos() {
        List<Pagamento> pagamentos = repository.findAll();
        return pagamentos.stream()
                .map(PagamentoResponseDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<Pagamento> getById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Optional<Pagamento> updatePagamento(Long id, PagamentoRequestDTO data) {
        Optional<Pagamento> pagamentoOptional = repository.findById(id);
        if (pagamentoOptional.isPresent()) {
            Pagamento pagamento = pagamentoOptional.get();
            pagamento.setValor(data.valor());
            repository.save(pagamento);
            return Optional.of(pagamento);
        }
        return Optional.empty();
    }
}
