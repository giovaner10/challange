package br.com.omnilink.desafio.DTO.request.vehicle;

import br.com.omnilink.desafio.enums.vehicle.VehicleBrand;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record VehicleRequestCreat(

        @NotBlank String plate,

        @NotBlank String model,

        @NotBlank Integer modelYear,

        @NotBlank String city,

        @NotBlank String state,

        @NotBlank String cep,

        @NotBlank Integer brand,

        @NotBlank Integer category,

        @NotBlank Integer fuelType,

        @NotBlank Integer status,

        @NotBlank Integer costumerId
) { }
