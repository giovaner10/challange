package br.com.omnilink.challange.service;

import br.com.omnilink.challange.DTO.request.costumer.CostumerRequestCreat;
import br.com.omnilink.challange.DTO.response.CostumerResponse;
import br.com.omnilink.challange.model.Costumer;

import java.util.List;

public interface ICostumerService {

    Costumer findById(Integer id);

    List<CostumerResponse> findAll();

    void save(CostumerRequestCreat request);

    void update(CostumerRequestCreat request, Integer id);

    void delete(Integer id);
}
