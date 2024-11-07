package com.tads.me.controller;

import com.tads.me.entity.Orcamento;
import com.tads.me.dto.OrcamentoRequestDTO;
import com.tads.me.dto.OrcamentoResponseDTO;
import com.tads.me.repository.OrcamentoRepository;
import com.tads.me.service.OrcamentoService;
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
@RequestMapping("/orcamento")
@Tag(name = "Orçamento", description = "Gerenciamento de Orçamentos")
public class OrcamentoController {

    @Autowired
    private OrcamentoService orcamentoService;

    @Autowired
    private OrcamentoRepository repository;

    @Operation(summary = "Cria um novo orçamento", description = "Adiciona um novo orçamento ao sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Orçamento criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping("/create")
    public ResponseEntity<Orcamento> create(@RequestBody OrcamentoRequestDTO orcamentoRequestDTO) {
        try {
            Orcamento orcamento = orcamentoService.createOrcamento(orcamentoRequestDTO);
            return ResponseEntity.ok(orcamento);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Lista todos os orçamentos", description = "Recupera uma lista de todos os orçamentos registrados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de orçamentos retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum orçamento encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/read-all")
    public ResponseEntity<List<OrcamentoResponseDTO>> listarOrcamentos() {
        try {
            List<OrcamentoResponseDTO> orcamentos = orcamentoService.listarOrcamentos();
            if (orcamentos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(orcamentos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Obtém um orçamento por ID", description = "Recupera um orçamento específico pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Orçamento encontrado e retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Orçamento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/read/{id}")
    public ResponseEntity<Orcamento> getById(@PathVariable("id") Long id) {
        Optional<Orcamento> orcamentoOptional = orcamentoService.getById(id);
        return orcamentoOptional
                .map(orcamento -> new ResponseEntity<>(orcamento, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Atualiza um orçamento", description = "Atualiza as informações de um orçamento específico pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Orçamento atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Orçamento não encontrado para atualização"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Orcamento> updateOrcamento(@PathVariable("id") Long id, @RequestBody OrcamentoRequestDTO orcamentoRequestDTO) {
        try {
            Optional<Orcamento> updatedOrcamento = orcamentoService.updateOrcamento(id, orcamentoRequestDTO);
            if (updatedOrcamento.isPresent()) {
                return ResponseEntity.ok(updatedOrcamento.get());
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Exclui um orçamento", description = "Remove um orçamento específico pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Orçamento excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Orçamento não encontrado para exclusão"),
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
