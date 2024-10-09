package com.tads.me.service;

import com.tads.me.domain.funcionario.Funcionario;
import com.tads.me.domain.funcionario.FuncionarioRequestDTO;
import com.tads.me.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Transactional
    public Funcionario createFuncionario(FuncionarioRequestDTO data) {
        Funcionario newFuncionario = new Funcionario(data);
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
            existingFuncionario.setSenha(data.senha());
            repository.save(existingFuncionario);
            return Optional.of(existingFuncionario);
        }
        return Optional.empty();
    }
}
