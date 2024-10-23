package com.tads.me.repository;

import com.tads.me.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);
    boolean existsByEmail(String email);
    Optional<Cliente> findById(UUID id);
}
