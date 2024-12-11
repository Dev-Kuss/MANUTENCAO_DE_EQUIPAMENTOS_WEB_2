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
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

@RestController
@RequestMapping("/solicitacao")
@CrossOrigin(origins = "*")
public class SolicitacaoController {

    @Autowired
    private SolicitacaoService solicitacaoService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO', 'CLIENT')")
    public ResponseEntity<?> createSolicitacao(@RequestBody SolicitacaoRequestDTO data) {
        try {
            var solicitacao = solicitacaoService.createSolicitacao(data);  
            return new ResponseEntity<>(solicitacao, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());            
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
        }
    }

    @GetMapping("/read-all")
    public ResponseEntity<?> getAllSolicitacoes(
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
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Internal Server Error");
            errorResponse.put("message", e.getMessage());
            errorResponse.put("stackTrace", Arrays.toString(e.getStackTrace()));
            
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
        }
    }

    @GetMapping("/read/{id}")
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

    @PatchMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO')")
    public ResponseEntity<?> patchSolicitacao(
            @PathVariable("id") Long id,
            @RequestBody Map<String, Object> updates) {
        try {
            if (id == null) {
                return ResponseEntity.badRequest().body("ID não pode ser nulo.");
            }
            System.out.println("ID recebido: " + id); // Log para depuração
            System.out.println("Updates recebidos: " + updates); // Log para depuração
            
            var solicitacaoData = solicitacaoService.patchSolicitacao(id, updates);
            return solicitacaoData
                    .map(solicitacao -> new ResponseEntity<>(solicitacao, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Internal Server Error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorResponse);
        }
    }
    
}
