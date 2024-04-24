package br.com.omnilink.desafio.DTO.response;

import br.com.omnilink.desafio.enums.customer.CostumerType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public record CostumerResponse(
        String name,
        String cnpj,
        String email,
        CostumerType costumerType
) {}