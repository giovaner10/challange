package br.com.omnilink.desafio.model;

import br.com.omnilink.desafio.enums.vehicle.VehicleBrand;
import br.com.omnilink.desafio.enums.vehicle.VehicleCategory;
import br.com.omnilink.desafio.enums.vehicle.VehicleFuelType;
import br.com.omnilink.desafio.enums.vehicle.VehicleStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "model_year")
    private int modelYear;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column(name = "brand")
    @Enumerated(EnumType.STRING)
    private VehicleBrand brand;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private VehicleCategory category;

    @Column(name = "fuel_type")
    @Enumerated(EnumType.STRING)
    private VehicleFuelType fuelType;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private VehicleStatus status;
}

