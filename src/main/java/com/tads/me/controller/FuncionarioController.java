package com.tads.me.controller;

import com.tads.me.domain.funcionario.Funcionario;
import com.tads.me.domain.funcionario.FuncionarioRequestDTO;
import com.tads.me.domain.funcionario.FuncionarioResponseDTO;
import com.tads.me.repositories.FuncionarioRepository;
import com.tads.me.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/funcionario")
class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @Autowired
    FuncionarioRepository repository;

    @PostMapping("/persistir")
    public ResponseEntity<Funcionario> create(@RequestBody FuncionarioRequestDTO data) {
        Funcionario newFuncionario = this.funcionarioService.createFuncionario(data);
        return ResponseEntity.ok(newFuncionario);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<FuncionarioResponseDTO>> getAll() {
        try {
            List<Funcionario> items = repository.findAll();

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            List<FuncionarioResponseDTO> responseItems = items.stream()
                    .map(FuncionarioResponseDTO::new)
                    .toList();

            return new ResponseEntity<>(responseItems, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Funcionario> getById(@PathVariable("id") Long id) {
        Optional<Funcionario> existingItemOptional = repository.findById(id);

        return existingItemOptional.map(funcionario -> new ResponseEntity<>(funcionario, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<Funcionario> updateFuncionario(@PathVariable Long id, @RequestBody FuncionarioRequestDTO data) {
        Optional<Funcionario> funcionarioOptional = this.funcionarioService.updateFuncionario(id, data);
        return funcionarioOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
