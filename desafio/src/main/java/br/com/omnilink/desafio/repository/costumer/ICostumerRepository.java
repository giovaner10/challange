package br.com.omnilink.desafio.repository.costumer;

import br.com.omnilink.desafio.model.Costumer;

import java.util.List;

public interface ICostumerRepository {

    Costumer findById(long id);

    List<Costumer> findAll();

    void save(Costumer user);

    void update(Costumer user);

    void delete(Costumer user);
}
