package br.com.omnilink.challange.DTO.request.costumer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CostumerRequest(
        @NotBlank String name,

        @NotBlank String cnpj,

        @NotBlank @Email String email,

        @NotNull Integer costumerType
) {}
