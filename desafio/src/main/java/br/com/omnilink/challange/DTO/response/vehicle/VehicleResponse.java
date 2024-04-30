package br.com.omnilink.challange.DTO.response.vehicle;

import br.com.omnilink.challange.DTO.response.CostumerResponse;
import lombok.Builder;

import java.io.Serializable;

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
) implements Serializable {
}
