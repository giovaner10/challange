package br.com.omnilink.desafio.model;

import br.com.omnilink.desafio.enums.vehicle.VehicleBrand;
import br.com.omnilink.desafio.enums.vehicle.VehicleCategory;
import br.com.omnilink.desafio.enums.vehicle.VehicleFuelType;
import br.com.omnilink.desafio.enums.vehicle.VehicleStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;

@Entity
@Table(name = "vehicle")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "plate", unique = true, nullable = false)
    private String plate;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "model_year", nullable = false)
    @Min(1900)
    private Integer modelYear;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @Column(name = "brand", nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleBrand brand;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleCategory category;

    @Column(name = "fuel_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleFuelType fuelType;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleStatus status;

    @ManyToOne
    @JoinColumn(name = "costumer")
    private Costumer costumer;
}
