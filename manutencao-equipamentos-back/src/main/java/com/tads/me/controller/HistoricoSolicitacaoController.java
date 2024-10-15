package com.tads.me.controller;

import com.tads.me.domain.historicosolicitacao.HistoricoSolicitacao;
import com.tads.me.domain.historicosolicitacao.HistoricoSolicitacaoRequestDTO;
import com.tads.me.domain.historicosolicitacao.HistoricoSolicitacaoResponseDTO;
import com.tads.me.repository.HistoricoSolicitacaoRepository;
import com.tads.me.service.HistoricoSolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/historico-solicitacao")
public class HistoricoSolicitacaoController {

    @Autowired
    private HistoricoSolicitacaoService historicoSolicitacaoService;

    @Autowired
    private HistoricoSolicitacaoRepository repository;

    @PostMapping("/create")
    public ResponseEntity<HistoricoSolicitacao> create(@RequestBody HistoricoSolicitacaoRequestDTO historicoRequestDTO) {
        try {
            HistoricoSolicitacao historico = historicoSolicitacaoService.createHistorico(historicoRequestDTO);
            return ResponseEntity.ok(historico);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/read-all")
    public ResponseEntity<List<HistoricoSolicitacaoResponseDTO>> listarHistoricos() {
        try {
            List<HistoricoSolicitacaoResponseDTO> historicos = historicoSolicitacaoService.listarHistoricos();
            if (historicos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(historicos);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<HistoricoSolicitacao> getById(@PathVariable("id") Long id) {
        Optional<HistoricoSolicitacao> historicoOptional = historicoSolicitacaoService.getById(id);
        return historicoOptional
                .map(historico -> new ResponseEntity<>(historico, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HistoricoSolicitacao> updateHistorico(@PathVariable("id") Long id, @RequestBody HistoricoSolicitacaoRequestDTO historicoRequestDTO) {
        try {
            Optional<HistoricoSolicitacao> updatedHistorico = historicoSolicitacaoService.updateHistorico(id, historicoRequestDTO);
            if (updatedHistorico.isPresent()) {
                return ResponseEntity.ok(updatedHistorico.get());
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
