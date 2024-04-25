package br.com.omnilink.desafio.DTO.response.vehicle;

import br.com.omnilink.desafio.DTO.response.CostumerResponse;
import lombok.Builder;

@Builder
public record VehicleResponse(

        Integer id,

        String plate,

        String model,

        Integer modelYear,

        String city,

        String state,

        String brand,

        String category,

        String fuelType,

        String status,

        CostumerResponse costumer
) {
}
