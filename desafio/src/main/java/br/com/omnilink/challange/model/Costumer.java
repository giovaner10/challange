package br.com.omnilink.challange.model;

import br.com.omnilink.challange.enums.customer.CostumerType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

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
}