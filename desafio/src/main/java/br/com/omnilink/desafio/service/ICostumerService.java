package br.com.omnilink.desafio.service;

import br.com.omnilink.desafio.DTO.request.costumer.CostumerRequestCreat;
import br.com.omnilink.desafio.DTO.response.CostumerResponse;
import br.com.omnilink.desafio.model.Costumer;

import java.util.List;

public interface ICostumerService {

    Costumer findById(Integer id);

    List<CostumerResponse> findAll();

    void save(CostumerRequestCreat request);

    void update(CostumerRequestCreat request, Integer id);

    void delete(Integer id);
}
