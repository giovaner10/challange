package br.com.omnilink.challange.factory;

import br.com.omnilink.challange.DTO.request.vehicle.VehicleRequest;
import br.com.omnilink.challange.DTO.response.vehicle.VehicleResponse;
import br.com.omnilink.challange.enums.vehicle.VehicleBrand;
import br.com.omnilink.challange.enums.vehicle.VehicleCategory;
import br.com.omnilink.challange.enums.vehicle.VehicleFuelType;
import br.com.omnilink.challange.enums.vehicle.VehicleStatus;
import br.com.omnilink.challange.model.Vehicle;

import java.time.LocalDate;

public class VehicleFactory {

    public static Vehicle getVehicle() {
        return Vehicle.builder()
                .id(1)
                .plate("ABC123")
                .model("Model X")
                .modelYear(2023)
                .city("Caturité")
                .state("PB")
                .registrationDate(LocalDate.now())
                .brand(VehicleBrand.TESLA)
                .category(VehicleCategory.TRUCK)
                .fuelType(VehicleFuelType.ELECTRIC)
                .status(VehicleStatus.CIRCULATING)
                .costumer(CostumerFactory.getCostumer())
                .build();
    }

    public static VehicleRequest getRequest() {
        return VehicleRequest.builder()
                .plate("ABC123")
                .model("Model X")
                .modelYear(2023)
                .city("Caturité")
                .cep("58455000")
                .state("PB")
                .brand(7)
                .category(3)
                .fuelType(5)
                .status(1)
                .costumerId(1)
                .build();
    }

    public static VehicleResponse getResponse() {
        return VehicleResponse.builder()
                .id(1)
                .plate("ABC123")
                .model("Model X")
                .modelYear(2023)
                .city("Caturité")
                .state("PB")
                .brand(VehicleBrand.TESLA.name())
                .category(VehicleCategory.TRUCK.getTranslationPortuguese())
                .fuelType(VehicleFuelType.ELECTRIC.getTranslationPortuguese())
                .status(VehicleStatus.CIRCULATING.getTranslationPortuguese())
                .costumer(CostumerFactory.getResponse())
                .build();
    }
}
