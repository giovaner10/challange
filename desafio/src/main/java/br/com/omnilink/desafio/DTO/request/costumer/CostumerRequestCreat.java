package br.com.omnilink.desafio.DTO.request.costumer;

import br.com.omnilink.desafio.enums.customer.CostumerType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

@Getter
@Setter
public class CostumerRequestCreat {

    @NotBlank
    private String name;

    @NotBlank
    @CNPJ
    private String cnpj;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private Integer costumerType;
}
