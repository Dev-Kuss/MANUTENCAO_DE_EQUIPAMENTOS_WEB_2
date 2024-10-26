package com.tads.me.controller;

import com.tads.me.entity.Funcionario;
import com.tads.me.dto.FuncionarioRequestDTO;
import com.tads.me.dto.FuncionarioResponseDTO;
import com.tads.me.entity.User;
import com.tads.me.repository.FuncionarioRepository;
import com.tads.me.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionario")
class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @Autowired
    FuncionarioRepository repository;

    @PreAuthorize("hasRole('ADMIN')")  // Apenas administradores podem criar funcionários
    @PostMapping("/create")
    public ResponseEntity<Funcionario> create(@RequestBody FuncionarioRequestDTO data, @AuthenticationPrincipal User authenticatedUser) throws NoSuchAlgorithmException {
        Funcionario newFuncionario = this.funcionarioService.createFuncionario(data, authenticatedUser);  // Passar o usuário autenticado
        return ResponseEntity.ok(newFuncionario);
    }


    @GetMapping("/read-all")
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

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/read/{id}")
    public ResponseEntity<Funcionario> getById(@PathVariable("id") Long id) {
        Optional<Funcionario> existingItemOptional = repository.findById(id);

        return existingItemOptional.map(funcionario -> new ResponseEntity<>(funcionario, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Funcionario> updateFuncionario(@PathVariable Long id, @RequestBody FuncionarioRequestDTO data) throws NoSuchAlgorithmException {
        Optional<Funcionario> funcionarioOptional = this.funcionarioService.updateFuncionario(id, data);

        return funcionarioOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable Long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}