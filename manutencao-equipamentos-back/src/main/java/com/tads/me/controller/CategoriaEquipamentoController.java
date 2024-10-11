package com.tads.me.controller;

import com.tads.me.domain.categoriaequipamento.CategoriaEquipamento;
import com.tads.me.domain.categoriaequipamento.CategoriaEquipamentoRequestDTO;
import com.tads.me.domain.categoriaequipamento.CategoriaEquipamentoResponseDTO;
import com.tads.me.repository.CategoriaEquipamentoRepository;
import com.tads.me.service.CategoriaEquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoria-equipamento")
public class CategoriaEquipamentoController {

    @Autowired
    private CategoriaEquipamentoService categoriaEquipamentoService;

    @Autowired
    private CategoriaEquipamentoRepository repository;

    @PostMapping("/create")
    public ResponseEntity<CategoriaEquipamento> create(@RequestBody CategoriaEquipamentoRequestDTO categoriaEquipamentoRequestDTO) {
        try {
            CategoriaEquipamento categoriaEquipamento = categoriaEquipamentoService.createCategoria(categoriaEquipamentoRequestDTO);
            return ResponseEntity.ok(categoriaEquipamento);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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

    @GetMapping("/read/{id}")
    public ResponseEntity<CategoriaEquipamento> getById(@PathVariable("id") Long id) {
        Optional<CategoriaEquipamento> categoriaEquipamentoOptional = categoriaEquipamentoService.getById(id);
        return categoriaEquipamentoOptional
                .map(categoriaEquipamento -> new ResponseEntity<>(categoriaEquipamento, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoriaEquipamento> updateCategoria(@PathVariable("id") Long id, @RequestBody CategoriaEquipamentoRequestDTO categoriaEquipamentoRequestDTO) {
        try {
            Optional<CategoriaEquipamento> updatedCategoriaEquipamento = categoriaEquipamentoService.updateCategoria(id, categoriaEquipamentoRequestDTO);
            if (updatedCategoriaEquipamento.isPresent()) {
                return ResponseEntity.ok(updatedCategoriaEquipamento.get());
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
