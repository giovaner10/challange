package br.com.omnilink.desafio.mapper.costumer;

import br.com.omnilink.desafio.DTO.request.costumer.CostumerRequestCreat;
import br.com.omnilink.desafio.model.Costumer;

public class CostumerMapper {

    public static Costumer Costumer(CostumerRequestCreat request){
        return Costumer.builder()
                .name(request.name())
                .cnpj(request.cnpj())
                .email(request.email())
                .build();
    }
}
