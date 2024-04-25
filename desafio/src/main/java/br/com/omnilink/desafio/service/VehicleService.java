package br.com.omnilink.desafio.service;

import br.com.omnilink.desafio.DTO.request.costumer.CostumerRequestCreat;
import br.com.omnilink.desafio.DTO.response.CostumerResponse;
import br.com.omnilink.desafio.exception.ObjectNotFoundException;
import br.com.omnilink.desafio.mapper.costumer.CostumerMapper;
import br.com.omnilink.desafio.model.Costumer;
import br.com.omnilink.desafio.repository.costumer.CostumerRepositoryImpl;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    CostumerRepositoryImpl costumerRepository;

    private static final Logger logger = LoggerFactory.getLogger(VehicleService.class);
    public Costumer findByIdOrThrowObjectNotFoundException(Integer id) throws BadRequestException {

        return costumerRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Costumer not Found."));
    }

    private void existByEmailOrCnpj(String email, String cnpj) throws BadRequestException {
        if (costumerRepository.existsByEmailOrCnpj(email, cnpj)) throw new BadRequestException("Costumer is present");
    }

    public List<CostumerResponse> findAll() {
        logger.info("Listando tudo.");

        return costumerRepository.findAll()
                .stream()
                .map(CostumerMapper::toResponse)
                .toList();
    }

    @Transactional
    public void save(CostumerRequestCreat request) throws BadRequestException {

        existByEmailOrCnpj(request.email(), request.cnpj());

        Costumer costumerSave = CostumerMapper.toEntity(request);

        costumerRepository.save(costumerSave);
    }

    @Transactional
    public void update(CostumerRequestCreat request, Integer id) throws BadRequestException {

        existByEmailOrCnpj(request.email(), request.cnpj());

        findByIdOrThrowObjectNotFoundException(id);

        Costumer costumerUpdate = CostumerMapper.toEntity(request);

        costumerUpdate.setId(id);

        costumerRepository.saveAndFlush(costumerUpdate);
    }

    @Transactional
    public void delete(Integer id) throws BadRequestException {

        Costumer byId = findByIdOrThrowObjectNotFoundException(id);

        costumerRepository.delete(byId);
    }
}
