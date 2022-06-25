package com.coderbank.cliente.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderbank.cliente.domain.model.Cliente;
import com.coderbank.cliente.domain.model.dto.ClienteDTO;
import com.coderbank.cliente.domain.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public ClienteDTO save(ClienteDTO clienteDTO) {
        var cliente = new Cliente();

        BeanUtils.copyProperties(clienteDTO, cliente);

        cliente = clienteRepository.save(cliente);

        clienteDTO.setId(cliente.getId());

        return clienteDTO;
    }
    
    public List<ClienteDTO> findAll() {
        var clientesDTO = new ArrayList<ClienteDTO>();

        clienteRepository.findAll().stream().forEach(cliente -> {

            var clienteDTO = new ClienteDTO();

            BeanUtils.copyProperties(cliente, clienteDTO);

            clientesDTO.add(clienteDTO);
        });

        return clientesDTO;
    }

    public Optional<ClienteDTO> findById(UUID id) {
        var clienteDTO = new ClienteDTO();

        var cliente = clienteRepository.findById(id);

        if(cliente.isPresent()){
            BeanUtils.copyProperties(cliente.get(), clienteDTO);
            return Optional.of(clienteDTO);
        }

        return Optional.empty();
    }

    @Transactional
    public void delete(ClienteDTO clienteDTO) {
        var cliente = new Cliente();

        BeanUtils.copyProperties(clienteDTO, cliente);

        clienteRepository.delete(cliente);
    }

}