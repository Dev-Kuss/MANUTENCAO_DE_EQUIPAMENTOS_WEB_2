package com.tads.me.service;

import com.tads.me.entity.Funcionario;
import com.tads.me.dto.FuncionarioRequestDTO;
import com.tads.me.entity.User;
import com.tads.me.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Transactional
    public Funcionario createFuncionario(FuncionarioRequestDTO data, User user) {
        // Use o construtor que recebe FuncionarioRequestDTO e User
        Funcionario newFuncionario = new Funcionario(data, user);
        repository.save(newFuncionario);
        return newFuncionario;
    }

    @Transactional
    public Optional<Funcionario> updateFuncionario(Long id, FuncionarioRequestDTO data) {
        Optional<Funcionario> funcionarioOptional = repository.findById(id);
        if (funcionarioOptional.isPresent()) {
            Funcionario existingFuncionario = funcionarioOptional.get();
            existingFuncionario.setNome(data.nome());
            existingFuncionario.setEmail(data.email());
            existingFuncionario.setDataNascimento(data.dataNascimento());
            existingFuncionario.setPasswordHash(data.senha());
            repository.save(existingFuncionario);
            return Optional.of(existingFuncionario);
        }
        return Optional.empty();
    }
}
