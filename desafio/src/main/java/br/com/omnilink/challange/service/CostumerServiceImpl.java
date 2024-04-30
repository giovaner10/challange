package br.com.omnilink.challange.service;

import br.com.omnilink.challange.DTO.request.costumer.CostumerRequestCreat;
import br.com.omnilink.challange.DTO.response.CostumerResponse;
import br.com.omnilink.challange.exception.BadRequestException;
import br.com.omnilink.challange.exception.ObjectNotFoundException;
import br.com.omnilink.challange.mapper.costumer.CostumerMapper;
import br.com.omnilink.challange.model.Costumer;
import br.com.omnilink.challange.repository.costumer.CostumerRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CostumerServiceImpl implements ICostumerService{

    @Autowired
    CostumerRepository costumerRepository;

    // private static final Logger logger = LoggerFactory.getLogger(CostumerService.class);
    @Override
    public Costumer findById(Integer id) {

        return findByIdOrThrowObjectNotFoundException(id);
    }

    @Override
    public List<CostumerResponse> findAll() {
        // logger.info("Listando tudo.");

        return costumerRepository.findAll()
                .stream()
                .map(CostumerMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void save(CostumerRequestCreat request) {

        existByEmailOrCnpj(request.email(), request.cnpj());

        Costumer costumerSave = CostumerMapper.toEntity(request);

        costumerRepository.save(costumerSave);
    }

    @Override
    @Transactional
    public void update(CostumerRequestCreat request, Integer id) {

        findByIdOrThrowObjectNotFoundException(id);

        existByEmailOrCnpjAndId(request.email(), request.cnpj(), id);

        Costumer costumerUpdate = CostumerMapper.toEntity(request);

        costumerUpdate.setId(id);

        costumerRepository.update(costumerUpdate);
    }

    @Override
    @Transactional
    public void delete(Integer id) {

        Costumer byId = findByIdOrThrowObjectNotFoundException(id);

        costumerRepository.delete(byId.getId());
    }

    private Costumer findByIdOrThrowObjectNotFoundException(Integer id) {
        return costumerRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Costumer not Found."));
    }

    private void existByEmailOrCnpj(String email, String cnpj) {
        if (costumerRepository.existsByEmailOrCnpj(email, cnpj)) {
            throw new BadRequestException("Email or cnpj is presents.");
        }
    }

    private void existByEmailOrCnpjAndId(String email, String cnpj, Integer id) {
        if (costumerRepository.existsByEmailOrCnpjAndId(email, cnpj, id)) {
            throw new BadRequestException("Email or cnpj is presents.");
        }
    }
}
