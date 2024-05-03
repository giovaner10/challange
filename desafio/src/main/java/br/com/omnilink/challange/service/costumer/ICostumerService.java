package br.com.omnilink.challange.service.costumer;

import br.com.omnilink.challange.DTO.request.costumer.CostumerRequest;
import br.com.omnilink.challange.DTO.response.costumer.CostumerResponse;
import br.com.omnilink.challange.model.Costumer;

import java.util.List;

public interface ICostumerService {

    CostumerResponse findById(Integer id);

    Costumer findByIdOrThrowObjectNotFoundException(Integer id);

    List<CostumerResponse> findAll();

    void save(CostumerRequest request);

    void update(CostumerRequest request, Integer id);

    void delete(Integer id);
}
