package com.coderbank.cliente.domain.service;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.coderbank.cliente.domain.exception.ClienteNotFoundException;
import com.coderbank.cliente.domain.exception.NegocioException;
import com.coderbank.cliente.domain.model.Cliente;
import com.coderbank.cliente.domain.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        var clienteExistente = clienteRepository.findByCpf(cliente.getCpf());

        if (clienteExistente.isPresent() && !clienteExistente.get().equals(cliente)) {
            throw new NegocioException(
                String.format("Já existe um cliente cadastrado com o CPF %s", cliente.getCpf()));
        }

        return clienteRepository.save(cliente);
    }

    public Cliente buscarOuFalhar(UUID clienteId) {
        return clienteRepository.findById(clienteId)
            .orElseThrow(() -> new ClienteNotFoundException(clienteId));
    }

    @Transactional
    public void excluir(UUID clienteId) {
        try {
            clienteRepository.deleteById(clienteId);
            clienteRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new ClienteNotFoundException(clienteId);
        }
    }

}