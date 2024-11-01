package com.tads.me.controller;

import com.tads.me.entity.Solicitacao;
import com.tads.me.dto.SolicitacaoRequestDTO;
import com.tads.me.dto.SolicitacaoResponseDTO;
import com.tads.me.repository.SolicitacaoRepository;
import com.tads.me.service.SolicitacaoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/solicitacao")
@Tag(name = "Solicitação", description = "Gerenciamento de Solicitações")
public class SolicitacaoController {

    @Autowired
    private SolicitacaoService solicitacaoService;

    @Autowired
    private SolicitacaoRepository repository;

    @Operation(summary = "Cria uma nova solicitação", description = "Adiciona uma nova solicitação ao sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Solicitação criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
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

    @Operation(summary = "Lista todas as solicitações", description = "Recupera uma lista de todas as solicitações registradas.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de solicitações retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma solicitação encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
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

    @Operation(summary = "Obtém uma solicitação por ID", description = "Recupera uma solicitação específica pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Solicitação encontrada e retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Solicitação não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/read/{id}")
    public ResponseEntity<Solicitacao> getById(@PathVariable("id") Long id) {
        Optional<Solicitacao> solicitacaoOptional = solicitacaoService.getById(id);
        return solicitacaoOptional
                .map(solicitacao -> new ResponseEntity<>(solicitacao, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Atualiza uma solicitação", description = "Atualiza as informações de uma solicitação específica pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Solicitação atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Solicitação não encontrada para atualização"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
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

    @Operation(summary = "Exclui uma solicitação", description = "Remove uma solicitação específica pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Solicitação excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Solicitação não encontrada para exclusão"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
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
