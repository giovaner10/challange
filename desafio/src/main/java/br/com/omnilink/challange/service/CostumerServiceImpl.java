package br.com.omnilink.challange.service;

import br.com.omnilink.challange.DTO.request.costumer.CostumerRequestCreat;
import br.com.omnilink.challange.DTO.response.CostumerResponse;
import br.com.omnilink.challange.exception.BadRequestException;
import br.com.omnilink.challange.exception.ObjectNotFoundException;
import br.com.omnilink.challange.mapper.costumer.CostumerMapper;
import br.com.omnilink.challange.model.Costumer;
import br.com.omnilink.challange.repository.costumer.CostumerRepository;
import br.com.omnilink.challange.security.security.UserDetailsLogged;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CostumerServiceImpl implements ICostumerService{

    @Autowired
    CostumerRepository costumerRepository;

    @Autowired
    UserDetailsLogged logged;

    private static final Logger logger = LoggerFactory.getLogger(CostumerServiceImpl.class);

    @Override
    @Transactional
    public void save(CostumerRequestCreat request) {
        logger.info("user: {} action: init save costumer email: " + request.email(), logged.getUsername());

        existByEmailOrCnpj(request.email(), request.cnpj());

        Costumer costumerSave = CostumerMapper.toEntity(request);

        costumerRepository.save(costumerSave);

        logger.info("user: {} - action: finaly save costumer email: " + request.email(), logged.getUsername());
    }

    @Override
    public CostumerResponse findById(Integer id) {

        logger.info("user: {} - action: find by id: " + id, logged.getUsername());

        Costumer byId = findByIdOrThrowObjectNotFoundException(id);

        return CostumerMapper.toResponse(byId);
    }

    @Override
    public List<CostumerResponse> findAll() {
        logger.info("user: {} - action: find all.", logged.getUsername());

        return costumerRepository.findAll()
                .stream()
                .map(CostumerMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void update(CostumerRequestCreat request, Integer id) {
        logger.info("user: {} - action:  init update costumer id: " + id, logged.getUsername());

        findByIdOrThrowObjectNotFoundException(id);

        existByEmailOrCnpjAndId(request.email(), request.cnpj(), id);

        Costumer costumerUpdate = CostumerMapper.toEntity(request);

        costumerUpdate.setId(id);

        costumerRepository.update(costumerUpdate);

        logger.info("user: {} - action: finaly update costumer id: " + id, logged.getUsername());
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        logger.info("user: {} - action: init delete costumer: " + id, logged.getUsername());

        Costumer byId = findByIdOrThrowObjectNotFoundException(id);

        costumerRepository.delete(byId.getId());

        logger.info("user: {} - action: finaly delete costumer: " + id, logged.getUsername());
    }

    public Costumer findByIdOrThrowObjectNotFoundException(Integer id) {
        Optional<Costumer> costumer = costumerRepository.findById(id);

        if (costumer.isEmpty()){
            logger.error("user: {} - Fail to find costumer is not present id: " + id + " - error: {}", logged.getUsername(), ObjectNotFoundException.class);
            throw new ObjectNotFoundException("Costumer not Found.");
        }

        return costumer.get();
    }

    private void existByEmailOrCnpj(String email, String cnpj) {
        if (costumerRepository.existsByEmailOrCnpj(email, cnpj)) {
            logger.error("user: {} - Fail to create costumer is present email/cnpj: " + email + " / " + cnpj + " - error: {}", logged.getUsername(), BadRequestException.class);
            throw new BadRequestException("Email or cnpj is presents.");
        }
    }

    private void existByEmailOrCnpjAndId(String email, String cnpj, Integer id) {
        if (costumerRepository.existsByEmailOrCnpjAndId(email, cnpj, id)) {
            logger.error("user: {} - Fail to update costumer id: " + id + " - error: {}", logged.getUsername(), BadRequestException.class);
            throw new BadRequestException("Email or cnpj is presents.");
        }
    }
}
