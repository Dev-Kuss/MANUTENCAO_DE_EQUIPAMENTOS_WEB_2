package com.tads.me.controller;

import com.tads.me.entity.CategoriaEquipamento;
import com.tads.me.dto.CategoriaEquipamentoRequestDTO;
import com.tads.me.dto.CategoriaEquipamentoResponseDTO;
import com.tads.me.repository.CategoriaEquipamentoRepository;
import com.tads.me.service.CategoriaEquipamentoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/categoria-equipamento")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Categoria Equipamento", description = "Gerenciamento das categorias de equipamentos")
public class CategoriaEquipamentoController {

    @Autowired
    private CategoriaEquipamentoService categoriaEquipamentoService;

    @Autowired
    private CategoriaEquipamentoRepository repository;

    @Operation(summary = "Cria uma nova categoria de equipamento", description = "Cria uma nova categoria de equipamento com as informações fornecidas.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoria criada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping("/create")
    public ResponseEntity<CategoriaEquipamento> create(
            @Parameter(description = "Dados para criar uma nova categoria") @RequestBody CategoriaEquipamentoRequestDTO categoriaEquipamentoRequestDTO) {
        try {
            CategoriaEquipamento categoriaEquipamento = categoriaEquipamentoService.createCategoria(categoriaEquipamentoRequestDTO);
            return ResponseEntity.ok(categoriaEquipamento);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Lista todas as categorias", description = "Retorna uma lista de todas as categorias de equipamentos.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de categorias retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma categoria encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/read-all")
    public ResponseEntity<List<CategoriaEquipamentoResponseDTO>> listarCategorias() {
        try {
            List<CategoriaEquipamentoResponseDTO> categorias = categoriaEquipamentoService.listarCategorias();
            if (categorias.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(categorias);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Busca uma categoria pelo ID", description = "Retorna os dados da categoria de equipamento correspondente ao ID informado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoria encontrada"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @GetMapping("/read/{id}")
    public ResponseEntity<CategoriaEquipamento> getById(
            @Parameter(description = "ID da categoria a ser buscada") @PathVariable("id") Long id) {
        Optional<CategoriaEquipamento> categoriaEquipamentoOptional = categoriaEquipamentoService.getById(id);
        return categoriaEquipamentoOptional
                .map(categoriaEquipamento -> new ResponseEntity<>(categoriaEquipamento, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Atualiza uma categoria existente", description = "Atualiza as informações da categoria de equipamento para o ID especificado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    @PutMapping("/update/{id}")
    public ResponseEntity<CategoriaEquipamento> updateCategoria(
            @Parameter(description = "ID da categoria a ser atualizada") @PathVariable("id") Long id,
            @Parameter(description = "Dados atualizados para a categoria") @RequestBody CategoriaEquipamentoRequestDTO categoriaEquipamentoRequestDTO) {
        try {
            Optional<CategoriaEquipamento> updatedCategoriaEquipamento = categoriaEquipamentoService.updateCategoria(id, categoriaEquipamentoRequestDTO);
            if (updatedCategoriaEquipamento.isPresent()) {
                return ResponseEntity.ok(updatedCategoriaEquipamento.get());
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Exclui uma categoria", description = "Exclui a categoria de equipamento correspondente ao ID informado.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Categoria excluída com sucesso"),
            @ApiResponse(responseCode = "409", description = "Categoria em uso, considere inativá-la"),
            @ApiResponse(responseCode = "417", description = "Falha ao excluir a categoria"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(
            @Parameter(description = "ID da categoria a ser excluída") @PathVariable("id") Long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Não é possível excluir a categoria porque ela está em uso. Considere inativá-la.");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
