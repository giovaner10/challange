package br.com.omnilink.desafio.DTO.response;

import br.com.omnilink.desafio.enums.customer.CostumerType;
import lombok.Builder;

@Builder
public record CostumerResponse(

        Integer id,

        String name,

        String cnpj,

        String email,

        CostumerType costumerType
) {
}