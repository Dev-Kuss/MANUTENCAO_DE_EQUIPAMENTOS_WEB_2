package com.tads.me.service;

import com.tads.me.domain.endereco.Endereco;
import com.tads.me.domain.endereco.EnderecoRequestDTO;
import com.tads.me.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    @Transactional
    public Endereco createEndereco(EnderecoRequestDTO data) {
        Endereco newEndereco = Endereco.builder()
                .cep(data.cep())
                .rua(data.rua())
                .numero(data.numero())
                .complemento(data.complemento())
                .bairro(data.bairro())
                .cidade(data.cidade())
                .estado(data.estado())
                .build();
        repository.save(newEndereco);
        return newEndereco;
    }

    @Transactional
    public Optional<Endereco> getEnderecoById(Long id) {
        return repository.findById(id);
    }
}
