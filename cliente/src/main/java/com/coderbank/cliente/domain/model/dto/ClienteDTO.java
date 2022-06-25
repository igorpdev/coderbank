package com.coderbank.cliente.domain.model.dto;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

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

    @NotBlank
    private String nome;

    @NotBlank
    @Size(min = 11, max = 11)
    @CPF
    private String cpf;

    private Integer idade;

    private String email;

    private String endereco;

}