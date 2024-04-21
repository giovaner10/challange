package br.com.omnilink.desafio.enums.vehicle;

public enum VehicleStatus {
    CIRCULATING(1, "Circulando"),
    ACCIDENT(2, "Acidente"),
    TRAFFIC_INFRACTION(3, "Infração de Trânsito"),
    MECHANICAL_ISSUE(4, "Problema Mecânico"),
    THEFT_OR_ROBBERY(5, "Roubo ou Furto"),
    MAINTENANCE_OR_REPAIR(6, "Manutenção ou Reparo"),
    PUBLIC_TRANSPORTATION(7, "Transporte Público"),
    OUT_OF_USE(8, "Fora de Uso ou Recuperação");

    private final int value;
    private final String translation;

    VehicleStatus(int value, String translation) {
        this.value = value;
        this.translation = translation;
    }

}
