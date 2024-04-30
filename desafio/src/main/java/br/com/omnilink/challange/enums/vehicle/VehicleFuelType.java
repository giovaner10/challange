package br.com.omnilink.challange.enums.vehicle;

import br.com.omnilink.challange.exception.TypetNotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum VehicleFuelType {
    GASOLINE(1, "Gasolina"),
    ALCOHOL(2, "Álcool"),
    DIESEL(3, "Diesel"),
    FLEX(4, "Flex"),
    ELECTRIC(5, "Elétrico"),
    HYBRID(6, "Híbrido");

    private final Integer idFuelType;
    private final String translationPortuguese;

    VehicleFuelType(Integer idFuelType, String translationPortuguese) {
        this.idFuelType = idFuelType;
        this.translationPortuguese = translationPortuguese;
    }

    public static VehicleFuelType fromIdFuelType(Integer idFuelType) {
        return Arrays.stream(values())
                .filter(vehicleFuelType -> vehicleFuelType.idFuelType.equals(idFuelType))
                .findFirst()
                .orElseThrow(() -> new TypetNotFoundException("Invalid code VehicleFuelType"));
    }
}
