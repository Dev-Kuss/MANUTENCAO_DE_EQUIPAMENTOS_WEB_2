package com.tads.me.controller;

import com.tads.me.entity.Solicitacao;
import com.tads.me.dto.SolicitacaoRequestDTO;
import com.tads.me.dto.SolicitacaoResponseDTO;
import com.tads.me.repository.SolicitacaoRepository;
import com.tads.me.service.SolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/solicitacao")
public class SolicitacaoController {

    @Autowired
    private SolicitacaoService solicitacaoService;

    @Autowired
    private SolicitacaoRepository repository;

    @PostMapping("/create")
    public ResponseEntity<Solicitacao> create(@RequestBody SolicitacaoRequestDTO solicitacaoRequestDTO) {
        try {
            Solicitacao solicitacao = solicitacaoService.createSolicitacao(solicitacaoRequestDTO);
            return ResponseEntity.ok(solicitacao);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/read-all")
    public ResponseEntity<List<SolicitacaoResponseDTO>> listarSolicitacoes() {
        try {
            List<SolicitacaoResponseDTO> solicitacoes = solicitacaoService.listarSolicitacoes();
            if (solicitacoes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(solicitacoes);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Solicitacao> getById(@PathVariable("id") Long id) {
        Optional<Solicitacao> solicitacaoOptional = solicitacaoService.getById(id);
        return solicitacaoOptional
                .map(solicitacao -> new ResponseEntity<>(solicitacao, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Solicitacao> updateSolicitacao(@PathVariable("id") Long id, @RequestBody SolicitacaoRequestDTO solicitacaoRequestDTO) {
        try {
            Optional<Solicitacao> updatedSolicitacao = solicitacaoService.updateSolicitacao(id, solicitacaoRequestDTO);
            if (updatedSolicitacao.isPresent()) {
                return ResponseEntity.ok(updatedSolicitacao.get());
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
