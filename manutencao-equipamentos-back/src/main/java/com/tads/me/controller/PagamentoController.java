package com.tads.me.controller;

import com.tads.me.entity.Pagamento;
import com.tads.me.dto.PagamentoRequestDTO;
import com.tads.me.dto.PagamentoResponseDTO;
import com.tads.me.repository.PagamentoRepository;
import com.tads.me.service.PagamentoService;
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
@RequestMapping("/pagamento")
@Tag(name = "Pagamento", description = "Gerenciamento de Pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private PagamentoRepository repository;

    @Operation(summary = "Cria um novo pagamento", description = "Adiciona um novo pagamento ao sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pagamento criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping("/create")
    public ResponseEntity<Pagamento> create(@RequestBody PagamentoRequestDTO pagamentoRequestDTO) {
        try {
            Pagamento pagamento = pagamentoService.createPagamento(pagamentoRequestDTO);
            return ResponseEntity.ok(pagamento);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Lista todos os pagamentos", description = "Recupera uma lista de todos os pagamentos registrados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de pagamentos retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum pagamento encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/read-all")
    public ResponseEntity<List<PagamentoResponseDTO>> listarPagamentos() {
        try {
            List<PagamentoResponseDTO> pagamentos = pagamentoService.listarPagamentos();
            if (pagamentos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(pagamentos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Obtém um pagamento por ID", description = "Recupera um pagamento específico pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pagamento encontrado e retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/read/{id}")
    public ResponseEntity<Pagamento> getById(@PathVariable("id") Long id) {
        Optional<Pagamento> pagamentoOptional = pagamentoService.getById(id);
        return pagamentoOptional
                .map(pagamento -> new ResponseEntity<>(pagamento, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Atualiza um pagamento", description = "Atualiza as informações de um pagamento específico pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pagamento atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado para atualização"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Pagamento> updatePagamento(@PathVariable("id") Long id, @RequestBody PagamentoRequestDTO pagamentoRequestDTO) {
        try {
            Optional<Pagamento> updatedPagamento = pagamentoService.updatePagamento(id, pagamentoRequestDTO);
            if (updatedPagamento.isPresent()) {
                return ResponseEntity.ok(updatedPagamento.get());
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Exclui um pagamento", description = "Remove um pagamento específico pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Pagamento excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado para exclusão"),
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
