package com.tads.me.service;

import com.tads.me.domain.cliente.Cliente;
import com.tads.me.domain.cliente.ClienteRequestDTO;
import com.tads.me.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Transactional
    public Cliente createCliente(ClienteRequestDTO data) {
        Cliente newCliente = new Cliente(data);
        repository.save(newCliente);
        return newCliente;
    }

    @Transactional
    public Optional<Cliente> updateCliente(Long id, ClienteRequestDTO data) {
        Optional<Cliente> clienteOptional = repository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente existingCliente = clienteOptional.get();
            existingCliente.setNome(data.nome());
            existingCliente.setCpf(data.cpf());
            existingCliente.setEmail(data.email());
            existingCliente.setCep(data.cep());
            existingCliente.setLogradouro(data.logradouro());
            existingCliente.setComplemento(data.complemento());
            existingCliente.setUnidade(data.unidade());
            existingCliente.setBairro(data.bairro());
            existingCliente.setLocalidade(data.localidade());
            existingCliente.setUf(data.uf());
            existingCliente.setEstado(data.estado());
            existingCliente.setRegiao(data.regiao());
            existingCliente.setIbge(data.ibge());
            existingCliente.setGia(data.gia());
            existingCliente.setDdd(data.ddd());
            existingCliente.setSiafi(data.siafi());
            repository.save(existingCliente);
            return Optional.of(existingCliente);
        }
        return Optional.empty();
    }
}
