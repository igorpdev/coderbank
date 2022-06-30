package com.coderbank.cliente.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderbank.cliente.domain.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    
    Optional<Cliente> findByCpf(String cpf);

}