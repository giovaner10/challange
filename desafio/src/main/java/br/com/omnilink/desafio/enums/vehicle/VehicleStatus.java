package br.com.omnilink.desafio.enums.vehicle;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum VehicleStatus {
    CIRCULATING(1, "Circulando"),
    ACCIDENT(2, "Acidente"),
    TRAFFIC_INFRACTION(3, "Infração de Trânsito"),
    MECHANICAL_ISSUE(4, "Problema Mecânico"),
    THEFT_OR_ROBBERY(5, "Roubo ou Furto"),
    MAINTENANCE_OR_REPAIR(6, "Manutenção ou Reparo"),
    PUBLIC_TRANSPORTATION(7, "Transporte Público"),
    OUT_OF_USE(8, "Fora de Uso ou Recuperação");

    private final Integer idStatus;
    private final String translationPortuguese;

    VehicleStatus(int idStatus, String translationPortuguese) {
        this.idStatus = idStatus;
        this.translationPortuguese = translationPortuguese;
    }

    public static VehicleStatus fromIdStatus(Integer idStatus) {
        return Arrays.stream(values())
                .filter(vehicleStatus -> vehicleStatus.idStatus.equals(idStatus))
                .findFirst()
                .orElseThrow();
    }
}
