package com.coderbank.cliente.domain.exception;

import java.util.UUID;

public class ClienteNotFoundException extends EntidadeNotFoundException {
    private static final long serialVersionUID = 1L;

    public ClienteNotFoundException(String mensagem) {
        super(mensagem);
    }

    public ClienteNotFoundException(UUID clienteId) {
        this(String.format("Não existe um cadastro de Cliente com o código %s", clienteId));
    }

}