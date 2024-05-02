package br.com.omnilink.challange.mapper.vehicle;

import br.com.omnilink.challange.DTO.request.vehicle.VehicleRequestCreat;
import br.com.omnilink.challange.DTO.response.vehicle.VehicleResponse;
import br.com.omnilink.challange.enums.vehicle.VehicleBrand;
import br.com.omnilink.challange.enums.vehicle.VehicleCategory;
import br.com.omnilink.challange.enums.vehicle.VehicleFuelType;
import br.com.omnilink.challange.enums.vehicle.VehicleStatus;
import br.com.omnilink.challange.mapper.costumer.CostumerMapper;
import br.com.omnilink.challange.model.Costumer;
import br.com.omnilink.challange.model.Vehicle;

import java.time.LocalDate;

public class VehicleMapper {

    public static Vehicle toEntity(VehicleRequestCreat request, Costumer costumer) {
        return Vehicle.builder()
                .plate(request.plate())
                .model(request.model())
                .modelYear(request.modelYear())
                .city(request.city())
                .state(request.state())
                .registrationDate(LocalDate.now())
                .brand(VehicleBrand.fromIdBrand(request.brand()))
                .category(VehicleCategory.fromIdCategory(request.category()))
                .fuelType(VehicleFuelType.fromIdFuelType(request.fuelType()))
                .status(VehicleStatus.fromIdStatus(request.status()))
                .costumer(costumer)
                .build();
    }

    public static Vehicle toEntityUpdate(VehicleRequestCreat request) {
        return Vehicle.builder()
                .plate(request.plate())
                .model(request.model())
                .modelYear(request.modelYear())
                .city(request.city())
                .state(request.state())
                .registrationDate(LocalDate.now())
                .brand(VehicleBrand.fromIdBrand(request.brand()))
                .category(VehicleCategory.fromIdCategory(request.category()))
                .fuelType(VehicleFuelType.fromIdFuelType(request.fuelType()))
                .status(VehicleStatus.fromIdStatus(request.status()))
                .build();
    }

    public static VehicleResponse toResponse(Vehicle vehicle) {
        return VehicleResponse.builder()
                .id(vehicle.getId())
                .plate(vehicle.getPlate())
                .model(vehicle.getModel())
                .modelYear(vehicle.getModelYear())
                .city(vehicle.getCity())
                .state(vehicle.getState())
                .brand(vehicle.getFuelType().getTranslationPortuguese())
                .category(vehicle.getCategory().getTranslationPortuguese())
                .fuelType(vehicle.getFuelType().getTranslationPortuguese())
                .status(vehicle.getStatus().getTranslationPortuguese())
                .costumer(CostumerMapper.toResponse(vehicle.getCostumer()))
                .build();
    }
}
