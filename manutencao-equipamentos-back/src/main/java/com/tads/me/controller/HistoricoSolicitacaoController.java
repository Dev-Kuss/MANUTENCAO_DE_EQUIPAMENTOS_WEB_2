package com.tads.me.controller;

import com.tads.me.dto.HistoricoSolicitacaoRequestDTO;
import com.tads.me.dto.HistoricoSolicitacaoResponseDTO;
import com.tads.me.service.HistoricoSolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historico")
@CrossOrigin(origins = "*")
public class HistoricoSolicitacaoController {

    @Autowired
    private HistoricoSolicitacaoService historicoSolicitacaoService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO', 'CLIENTE')")
    public ResponseEntity<HistoricoSolicitacaoResponseDTO> createHistorico(@RequestBody HistoricoSolicitacaoRequestDTO data) {
        try {
            var historico = historicoSolicitacaoService.createHistorico(data);
            return new ResponseEntity<>(historico, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/read-all")
    @PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO')")
    public ResponseEntity<List<HistoricoSolicitacaoResponseDTO>> getAllHistoricos() {
        try {
            List<HistoricoSolicitacaoResponseDTO> historicos = historicoSolicitacaoService.listarHistoricos();
            if (historicos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(historicos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/read/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO', 'CLIENTE')")
    public ResponseEntity<HistoricoSolicitacaoResponseDTO> getHistoricoById(@PathVariable("id") Long id) {
        var historicoData = historicoSolicitacaoService.getById(id);
        return historicoData
                .map(historico -> new ResponseEntity<>(historico, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/read-by-solicitacao/{solicitacaoId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO', 'CLIENTE')")
    public ResponseEntity<List<HistoricoSolicitacaoResponseDTO>> getHistoricosBySolicitacao(@PathVariable("solicitacaoId") Long solicitacaoId) {
        try {
            List<HistoricoSolicitacaoResponseDTO> historicos = historicoSolicitacaoService.listarHistoricosPorSolicitacao(solicitacaoId);
            if (historicos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(historicos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
