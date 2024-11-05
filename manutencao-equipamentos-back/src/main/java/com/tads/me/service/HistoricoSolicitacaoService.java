package com.tads.me.service;

import com.tads.me.entity.HistoricoSolicitacao;
import com.tads.me.entity.Solicitacao;
import com.tads.me.entity.Funcionario;
import com.tads.me.entity.Cliente;
import com.tads.me.dto.HistoricoSolicitacaoRequestDTO;
import com.tads.me.dto.HistoricoSolicitacaoResponseDTO;
import com.tads.me.repository.HistoricoSolicitacaoRepository;
import com.tads.me.repository.SolicitacaoRepository;
import com.tads.me.repository.FuncionarioRepository;
import com.tads.me.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HistoricoSolicitacaoService {

    @Autowired
    private HistoricoSolicitacaoRepository repository;

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public HistoricoSolicitacaoResponseDTO createHistorico(HistoricoSolicitacaoRequestDTO data) {
        Solicitacao solicitacao = solicitacaoRepository.findById(data.idSolicitacao())
            .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));

        Funcionario funcionario = null;
        Cliente cliente = null;

        if (data.idFuncionario() != null) {
            funcionario = funcionarioRepository.findById(data.idFuncionario())
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        }

        if (data.idCliente() != null) {
            cliente = clienteRepository.findById(data.idCliente())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        }

        HistoricoSolicitacao historico = HistoricoSolicitacao.builder()
                .dataHora(LocalDateTime.now())
                .descricao(data.descricao())
                .funcionario(funcionario)
                .cliente(cliente)
                .solicitacao(solicitacao)
                .build();

        repository.save(historico);
        return convertToResponseDTO(historico);
    }

    public List<HistoricoSolicitacaoResponseDTO> listarHistoricos() {
        List<HistoricoSolicitacao> historicos = repository.findAll();
        return historicos.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<HistoricoSolicitacaoResponseDTO> listarHistoricosPorSolicitacao(Long idSolicitacao) {
        List<HistoricoSolicitacao> historicos = repository.findBySolicitacaoIdSolicitacaoOrderByDataHoraDesc(idSolicitacao);
        return historicos.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<HistoricoSolicitacaoResponseDTO> getById(Long id) {
        return repository.findById(id)
                .map(this::convertToResponseDTO);
    }

    private HistoricoSolicitacaoResponseDTO convertToResponseDTO(HistoricoSolicitacao historico) {
        return new HistoricoSolicitacaoResponseDTO(
            historico.getId(),
            historico.getDataHora(),
            historico.getDescricao(),
            historico.getFuncionario() != null ? historico.getFuncionario().getNome() : null,
            historico.getCliente() != null ? historico.getCliente().getNome() : null,
            historico.getSolicitacao().getIdSolicitacao()
        );
    }
}
