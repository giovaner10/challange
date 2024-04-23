package br.com.omnilink.desafio.model;

import br.com.omnilink.desafio.enums.customer.CostumerType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class Costumer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cpf_cnpj", unique = true, nullable = false)
    private String cnpj;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "costumer_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CostumerType costumerType;

    @OneToMany(mappedBy = "costumer", cascade = CascadeType.ALL)
    private Set<Vehicle> vehicles;
}
