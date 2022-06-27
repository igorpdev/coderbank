package com.coderbank.cliente.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coderbank.cliente.domain.model.Cliente;
import com.coderbank.cliente.domain.model.dto.ClienteDTO;

@Component
public class ClienteModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ClienteDTO toModel(Cliente cliente) {
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public List<ClienteDTO> toCollectionModel(List<Cliente> clientes) {
        return clientes.stream()
            .map(cliente -> toModel(cliente))
            .collect(Collectors.toList());
    }
    
}