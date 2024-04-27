package br.com.omnilink.desafio.DTO.request.costumer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.br.CNPJ;

@Builder
public record CostumerRequestCreat(
        @NotBlank String name,

        @NotBlank @CNPJ String cnpj,

        @NotBlank @Email String email,

        @NotNull Integer costumerType
) {}
