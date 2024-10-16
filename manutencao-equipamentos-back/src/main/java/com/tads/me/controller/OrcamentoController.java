package com.tads.me.controller;

import com.tads.me.entity.Orcamento;
import com.tads.me.dto.OrcamentoRequestDTO;
import com.tads.me.dto.OrcamentoResponseDTO;
import com.tads.me.repository.OrcamentoRepository;
import com.tads.me.service.OrcamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orcamento")
public class OrcamentoController {

    @Autowired
    private OrcamentoService orcamentoService;

    @Autowired
    private OrcamentoRepository repository;

    @PostMapping("/create")
    public ResponseEntity<Orcamento> create(@RequestBody OrcamentoRequestDTO orcamentoRequestDTO) {
        try {
            Orcamento orcamento = orcamentoService.createOrcamento(orcamentoRequestDTO);
            return ResponseEntity.ok(orcamento);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Orcamento> getById(@PathVariable("id") Long id) {
        Optional<Orcamento> orcamentoOptional = orcamentoService.getById(id);
        return orcamentoOptional
                .map(orcamento -> new ResponseEntity<>(orcamento, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

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
