package com.tads.me.controller;

import com.tads.me.entity.HistoricoSolicitacao;
import com.tads.me.dto.HistoricoSolicitacaoRequestDTO;
import com.tads.me.dto.HistoricoSolicitacaoResponseDTO;
import com.tads.me.repository.HistoricoSolicitacaoRepository;
import com.tads.me.service.HistoricoSolicitacaoService;
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
@RequestMapping("/historico-solicitacao")
@Tag(name = "Histórioco Solicitação", description = "Gerenciamento o Histórico de Solicitações")
public class HistoricoSolicitacaoController {

    @Autowired
    private HistoricoSolicitacaoService historicoSolicitacaoService;

    @Autowired
    private HistoricoSolicitacaoRepository repository;

    @Operation(summary = "Cria um novo histórico de solicitação", description = "Adiciona um novo registro de histórico de solicitação.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Histórico de solicitação criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
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

    @Operation(summary = "Lista todos os históricos de solicitação", description = "Recupera uma lista de todos os registros de histórico de solicitação.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de históricos de solicitação retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum histórico de solicitação encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
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

    @Operation(summary = "Obtém um histórico de solicitação por ID", description = "Retorna um registro de histórico de solicitação específico pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Histórico de solicitação encontrado e retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Histórico de solicitação não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/read/{id}")
    public ResponseEntity<HistoricoSolicitacao> getById(@PathVariable("id") Long id) {
        Optional<HistoricoSolicitacao> historicoOptional = historicoSolicitacaoService.getById(id);
        return historicoOptional
                .map(historico -> new ResponseEntity<>(historico, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Atualiza um histórico de solicitação", description = "Atualiza as informações de um registro de histórico de solicitação específico pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Histórico de solicitação atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Histórico de solicitação não encontrado para atualização"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PreAuthorize("hasRole('ADMIN')")
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

    @Operation(summary = "Exclui um histórico de solicitação", description = "Remove um registro de histórico de solicitação específico pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Histórico de solicitação excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Histórico de solicitação não encontrado para exclusão"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PreAuthorize("hasRole('ADMIN')")
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
