package br.com.omnilink.challange.enums.customer;

import br.com.omnilink.challange.exception.TypetNotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum CostumerType {

    ROAD_FREIGHT_TRANSPORT(1, "Transportadora de Carga Rodoviária"),
    PASSENGER_TRANSPORT_BY_BUS(2, "Passenger Transport by Bus"),
    LOGISTICS_AND_DISTRIBUTION(3, "Logística e Distribuição Rodoviária"),
    SPECIAL_CARGO_TRANSPORT(4, "Transporte de Cargas Especiais"),
    TRANSPORT_OF_PERISHABLE_PRODUCTS(5, "Transporte de Produtos Perecíveis"),
    TRANSPORT_OF_HAZARDOUS_WASTE_AND_MATERIALS(6, "Transporte de Resíduos e Materiais Perigosos");

    private final Integer idCostumerType;
    private final String translationPortuguese;

    CostumerType(Integer idCostumerType, String translationPortuguese) {
        this.idCostumerType = idCostumerType;
        this.translationPortuguese = translationPortuguese;
    }

    public static CostumerType fromIdCostumerType(Integer idCostumerType) {
        return Arrays.stream(values())
                .filter(costumerType -> costumerType.idCostumerType.equals(idCostumerType))
                .findFirst()
                .orElseThrow(() -> new TypetNotFoundException("Invalid code CostumerType"));
    }
}
