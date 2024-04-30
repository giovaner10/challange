package br.com.omnilink.challange.mapper.costumer;

import br.com.omnilink.challange.DTO.request.costumer.CostumerRequestCreat;
import br.com.omnilink.challange.DTO.response.CostumerResponse;
import br.com.omnilink.challange.enums.customer.CostumerType;
import br.com.omnilink.challange.model.Costumer;

public class CostumerMapper {

    public static Costumer toEntity(CostumerRequestCreat request) {
        return Costumer.builder()
                .name(request.name())
                .cnpj(request.cnpj())
                .email(request.email())
                .costumerType(CostumerType.fromIdCostumerType(request.costumerType()))
                .build();
    }

    public static CostumerResponse toResponse(Costumer costumer) {
        return CostumerResponse.builder()
                .id(costumer.getId())
                .name(costumer.getName())
                .cnpj(costumer.getCnpj())
                .email(costumer.getEmail())
                .costumerType(costumer.getCostumerType())
                .build();
    }
}
