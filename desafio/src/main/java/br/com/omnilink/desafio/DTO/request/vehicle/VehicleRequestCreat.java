package br.com.omnilink.desafio.DTO.request.vehicle;

import br.com.omnilink.desafio.enums.vehicle.VehicleBrand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record VehicleRequestCreat(

        @NotBlank String plate,

        @NotBlank String model,

        @NotNull Integer modelYear,

        @NotBlank String city,

        @NotBlank String state,


        @NotBlank String cep,

        @NotNull Integer brand,

        @NotNull Integer category,

        @NotNull Integer fuelType,

        @NotNull Integer status,

        @NotNull Integer costumerId
) { }
