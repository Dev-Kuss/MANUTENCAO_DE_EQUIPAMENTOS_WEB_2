package com.tads.me.controller;

import com.tads.me.dto.SolicitacaoRequestDTO;
import com.tads.me.dto.SolicitacaoResponseDTO;
import com.tads.me.service.SolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/solicitacao")
@CrossOrigin(origins = "*")
public class SolicitacaoController {

    @Autowired
    private SolicitacaoService solicitacaoService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO', 'CLIENTE')")
    public ResponseEntity<SolicitacaoResponseDTO> createSolicitacao(@RequestBody SolicitacaoRequestDTO data) {
        try {
            var solicitacao = solicitacaoService.createSolicitacao(data);
            return new ResponseEntity<>(solicitacao, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/read-all")
    @PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO')")
    public ResponseEntity<List<SolicitacaoResponseDTO>> getAllSolicitacoes(
            @RequestParam(required = false) UUID usuarioId) {
        try {
            List<SolicitacaoResponseDTO> solicitacoes;
            if (usuarioId != null) {
                solicitacoes = solicitacaoService.listarSolicitacoesPorUsuario(usuarioId);
            } else {
                solicitacoes = solicitacaoService.listarSolicitacoes();
            }

            if (solicitacoes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(solicitacoes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/read/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO', 'CLIENTE')")
    public ResponseEntity<SolicitacaoResponseDTO> getSolicitacaoById(@PathVariable("id") Long id) {
        var solicitacaoData = solicitacaoService.getById(id);
        return solicitacaoData
                .map(solicitacao -> new ResponseEntity<>(solicitacao, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO')")
    public ResponseEntity<SolicitacaoResponseDTO> updateSolicitacao(
            @PathVariable("id") Long id,
            @RequestBody SolicitacaoRequestDTO data) {
        var solicitacaoData = solicitacaoService.updateSolicitacao(id, data);
        return solicitacaoData
                .map(solicitacao -> new ResponseEntity<>(solicitacao, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
