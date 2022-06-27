package com.coderbank.cliente.domain.model.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {
    
    private UUID id;
    private String nome;
    private String cpf;
    private Integer idade;
    private String email;
    private String endereco;

}