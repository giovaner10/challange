package br.com.omnilink.desafio.enums.vehicle;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum VehicleCategory {
    CAR(1, "Carro"),
    MOTORCYCLE(2, "Motocicleta"),
    TRUCK(3, "Caminhão"),
    BUS(4, "Ônibus"),
    VAN(5, "Van"),
    SUV(6, "SUV"),
    AUTOMOTOR(7, "Automotor"),
    OTHER(8, "Outro");

    private final Integer idCategory;
    private final String translationPortuguese;

    VehicleCategory(int idCategory, String translationPortuguese) {
        this.idCategory = idCategory;
        this.translationPortuguese = translationPortuguese;
    }

    public static VehicleCategory fromIdCategory(Integer idCategory) {
       return Arrays.stream(values())
                .filter(vehicleCategory -> vehicleCategory.idCategory.equals(idCategory))
                .findFirst()
                .orElseThrow();
    }
}
