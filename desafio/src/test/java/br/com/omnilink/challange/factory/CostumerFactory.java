package br.com.omnilink.challange.factory;

import br.com.omnilink.challange.DTO.request.costumer.CostumerRequest;
import br.com.omnilink.challange.DTO.response.costumer.CostumerResponse;
import br.com.omnilink.challange.enums.customer.CostumerType;
import br.com.omnilink.challange.model.Costumer;

public class CostumerFactory {

    public static Costumer getCostumer() {
        return Costumer.builder()
                .id(1)
                .name("Exemplo")
                .cnpj("12345678901234")
                .email("exemplo@example.com")
                .costumerType(CostumerType.ROAD_FREIGHT_TRANSPORT)
                .build();
    }

    public static CostumerRequest getRequest() {
        return CostumerRequest.builder()
                .name("Exemplo")
                .cnpj("12345678901234")
                .email("exemplo@example.com")
                .costumerType(1)
                .build();
    }


    public static CostumerResponse getResponse() {
        return CostumerResponse.builder()
                .id(1)
                .name("Exemplo")
                .cnpj("12345678901234")
                .email("exemplo@example.com")
                .costumerType(CostumerType.ROAD_FREIGHT_TRANSPORT)
                .build();
    }
}
