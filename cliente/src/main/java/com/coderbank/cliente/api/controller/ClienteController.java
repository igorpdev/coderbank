package com.coderbank.cliente.api.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.coderbank.cliente.api.assembler.ClienteModelAssembler;
import com.coderbank.cliente.domain.model.Cliente;
import com.coderbank.cliente.domain.model.dto.ClienteDTO;
import com.coderbank.cliente.domain.repository.ClienteRepository;
import com.coderbank.cliente.domain.service.ClienteService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteModelAssembler clienteModelAssembler;

    @GetMapping
    public List<ClienteDTO> listar() {
        List<Cliente> todosClientes = clienteRepository.findAll();

        return clienteModelAssembler.toCollectionModel(todosClientes);
    }

    @GetMapping("/{clienteId}")
    public ClienteDTO buscar(@PathVariable UUID clienteId) {
        Cliente cliente = clienteService.buscarOuFalhar(clienteId);

        return clienteModelAssembler.toModel(cliente);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO adicionar(@RequestBody @Valid Cliente cliente) {
        cliente = clienteService.salvar(cliente);

        return clienteModelAssembler.toModel(cliente);
    }

    @PutMapping("/{clienteId}")
    public ClienteDTO atualizar(@PathVariable @Valid UUID clienteId,
            @RequestBody @Valid Cliente cliente) {
        Cliente clienteAtual = clienteService.buscarOuFalhar(clienteId);
        
        BeanUtils.copyProperties(cliente, clienteAtual, "id");
        clienteAtual = clienteService.salvar(clienteAtual);

        return clienteModelAssembler.toModel(clienteAtual);
    }

    @DeleteMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable UUID clienteId) {
        clienteService.excluir(clienteId);
    }

}