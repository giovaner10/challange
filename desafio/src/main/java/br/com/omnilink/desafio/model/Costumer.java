package br.com.omnilink.desafio.model;

import br.com.omnilink.desafio.enums.customer.CostumerType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "costumer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class Costumer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cnpj", unique = true, nullable = false)
    private String cnpj;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "costumer_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CostumerType costumerType;

    @OneToMany(mappedBy = "costumer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Vehicle> vehicles;
}
