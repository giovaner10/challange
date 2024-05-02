package br.com.omnilink.challange.DTO.request.vehicle;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record VehicleRequestCreat(

        @NotBlank String plate,

        @NotBlank String model,

        @NotNull @Min(1900) Integer modelYear,

        @NotBlank String city,

        @NotBlank String state,

        @NotBlank String cep,

        @NotNull Integer brand,

        @NotNull Integer category,

        @NotNull Integer fuelType,

        @NotNull Integer status,

        @NotNull Integer costumerId
) { }
