package br.com.omnilink.challange.service.vehicle;

import br.com.omnilink.challange.DTO.request.vehicle.VehicleRequest;
import br.com.omnilink.challange.DTO.response.vehicle.VehicleResponse;
import br.com.omnilink.challange.exception.model.BadRequestException;
import br.com.omnilink.challange.exception.model.ObjectNotFoundException;
import br.com.omnilink.challange.mapper.vehicle.VehicleMapper;
import br.com.omnilink.challange.model.Costumer;
import br.com.omnilink.challange.model.Vehicle;
import br.com.omnilink.challange.repository.vehicle.VehicleRepository;
import br.com.omnilink.challange.security.security.UserDetailsLogged;
import br.com.omnilink.challange.service.costumer.ICostumerService;
import br.com.omnilink.challange.validator.CepValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehicleServiceImpl implements IVehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    ICostumerService costumerService;

    @Autowired
    UserDetailsLogged logged;

    private static final Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);

    @Override
    @Transactional
    public void save(VehicleRequest request) {
        logger.info("user: {} action: init save vehicle plate/user: " + request.plate() + "/" + request.costumerId(), logged.getUsername());

        CepValidator.validate(request);

        existByPlate(request.plate());

        Costumer byId = costumerService.findByIdOrThrowObjectNotFoundException(request.costumerId());

        Vehicle vehicleSave = VehicleMapper.toEntity(request, byId);

        vehicleRepository.save(vehicleSave);

        logger.info("user: {} action: saved vehicle plate/user: " + request.plate() + "/" + request.costumerId(), logged.getUsername());
    }

    @Override
    public VehicleResponse findById(Integer id) {
        logger.info("user: {} - action: find vehicle by id : " + id, logged.getUsername());

        Vehicle byId = this.findByIdOrThrowObjectNotFoundException(id);

        return VehicleMapper.toResponse(byId);
    }

    @Override
    public List<VehicleResponse> findAll() {
        logger.info("user: {} - action: find all vehicles.", logged.getUsername());

        return vehicleRepository.findAll()
                .stream()
                .map(VehicleMapper::toResponse)
                .toList();
    }

    @Override
    public List<VehicleResponse> findAllByCostumer(Integer id) {
        logger.info("user: {} - action: find all vehicles by costumer id: " + id, logged.getUsername());

        return vehicleRepository.findAllByCostumer(id)
                .stream()
                .map(VehicleMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void update(VehicleRequest request, Integer id) {
        logger.info("user: {} - action: init update vehicle id: " + id, logged.getUsername());

        existByPlateAndId(request.plate(), id);

        Vehicle vehicleUpdate = VehicleMapper.toEntityUpdate(request);

        vehicleUpdate.setId(id);

        vehicleRepository.saveAndFlush(vehicleUpdate);

        logger.info("user: {} - action: finally update vehicle id: " + id, logged.getUsername());
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        logger.info("user: {} - action: init delete vehicle: " + id, logged.getUsername());

        Vehicle byId = findByIdOrThrowObjectNotFoundException(id);

        vehicleRepository.delete(byId);

        logger.info("user: {} - action: finally delete vehicle: " + id, logged.getUsername());
    }

    private Vehicle findByIdOrThrowObjectNotFoundException(Integer id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Vehicle not Found."));
    }

    private void existByPlate(String plate)  {
        if (vehicleRepository.existsByPlate(plate)) {
            logger.error("user: {} - Fail to save vehicle plate is present, plate: " + plate + " - error: {}", logged.getUsername(), BadRequestException.class);
            throw new BadRequestException("Vehicle is present");
        }
    }

    private void existByPlateAndId(String plate, Integer id)  {
        if (!vehicleRepository.existsByPlateAndId(plate, id)) {
            logger.error("user: {} - Fail to save update plate is present, plate: " + plate + " - error: {}", logged.getUsername(), BadRequestException.class);
            throw new BadRequestException("Vehicle is present");
        }
    }
}
