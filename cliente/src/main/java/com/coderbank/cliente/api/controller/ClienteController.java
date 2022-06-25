package com.coderbank.cliente.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderbank.cliente.domain.model.dto.ClienteDTO;
import com.coderbank.cliente.domain.service.ClienteService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Object> saveCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(clienteDTO));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAllClientes() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneCliente(@PathVariable(value = "id") UUID id) {
        Optional<ClienteDTO> clienteDTOOptional = clienteService.findById(id);

        return clienteDTOOptional.<ResponseEntity<Object>>map(
            clienteDTO -> ResponseEntity.status(HttpStatus.OK).body(clienteDTO)).orElseGet(() -> 
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCliente(@PathVariable(value = "id") UUID id){
        Optional<ClienteDTO> clienteDTOOptional = clienteService.findById(id);

        if (clienteDTOOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }

        clienteService.delete(clienteDTOOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cliente excluído com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCliente(@PathVariable(value = "id") UUID id,
            @RequestBody @Valid ClienteDTO clienteDTORequest) {
        Optional<ClienteDTO> clienteDTOOptional = clienteService.findById(id);

        if (clienteDTOOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(clienteService.save(clienteDTORequest));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
    }

}