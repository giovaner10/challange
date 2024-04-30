package br.com.omnilink.challange.DTO.response;

import br.com.omnilink.challange.enums.customer.CostumerType;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record CostumerResponse (

        Integer id,

        String name,

        String cnpj,

        String email,

        CostumerType costumerType
) implements Serializable {
}