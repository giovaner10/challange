package br.com.omnilink.desafio.DTO.request.costumer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CostumerRequestCreat(
        @NotBlank String name,

        @NotBlank String cnpj,

        @NotBlank @Email String email,

        @NotNull Integer costumerType
) {}
