package br.com.omnilink.desafio.repository.costumer;

import br.com.omnilink.desafio.model.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostumerRepositoryImpl extends JpaRepository<Costumer , Integer> {

    boolean existsByEmailOrCnpj(String email, String cnpj);

}