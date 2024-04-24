package br.com.omnilink.desafio.service;

import br.com.omnilink.desafio.DTO.request.costumer.CostumerRequestCreat;
import br.com.omnilink.desafio.DTO.response.CostumerResponse;
import br.com.omnilink.desafio.mapper.costumer.CostumerMapper;
import br.com.omnilink.desafio.model.Costumer;
import br.com.omnilink.desafio.repository.costumer.CostumerRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostumerService {//implements IBaseRepository<Costumer> {

    @Autowired
    CostumerRepositoryImpl costumerRepository;

  //  @Override
    public Costumer findById(Integer id) {
        return costumerRepository.findById(id);
    }

 //   @Override
    public List<CostumerResponse> findAll() {
        return costumerRepository.findAll()
                .stream()
                .map(CostumerMapper::toResponse)
                .toList();
    }

 //   @Override
    public void save(CostumerRequestCreat request) {
        Costumer costumerSave = CostumerMapper.toEntity(request);
        costumerRepository.save(costumerSave);
    }

  //  @Override
    public void update(Costumer costumer) {
        costumerRepository.update(costumer);
    }

  //  @Override
    public void delete(Costumer costumer) {
        costumerRepository.delete(costumerRepository.findById(costumer.getId()));
    }
}
