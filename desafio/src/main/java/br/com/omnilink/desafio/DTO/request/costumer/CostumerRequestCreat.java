package br.com.omnilink.desafio.DTO.request.costumer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

@Getter
@Setter
@Builder
public record CostumerRequestCreat(
        @NotBlank String name,
        @NotBlank @CNPJ String cnpj,
        @NotBlank @Email String email,
        @NotBlank Integer costumerType
) {}
