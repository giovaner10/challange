package br.com.omnilink.challange.service;

import br.com.omnilink.challange.DTO.request.vehicle.VehicleRequestCreat;
import br.com.omnilink.challange.DTO.response.vehicle.VehicleResponse;
import br.com.omnilink.challange.exception.BadRequestException;
import br.com.omnilink.challange.exception.ObjectNotFoundException;
import br.com.omnilink.challange.mapper.vehicle.VehicleMapper;
import br.com.omnilink.challange.model.Costumer;
import br.com.omnilink.challange.model.Vehicle;
import br.com.omnilink.challange.repository.vehicle.VehicleRepository;
import br.com.omnilink.challange.validator.CepValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehicleServiceImpl implements IVehicleService{

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    ICostumerService costumerService;

    // private static final Logger logger = LoggerFactory.getLogger(VehicleService.class);
    @Override
    @Transactional
    public void save(VehicleRequestCreat request) throws JsonProcessingException {

        CepValidator.validate(request);

        existByPlate(request.plate());

        Costumer byId = costumerService.findById(request.costumerId());

        Vehicle vehicleSave = VehicleMapper.toEntity(request, byId);

        vehicleRepository.save(vehicleSave);
    }

    @Override
    public Vehicle findById(Integer id) {

        return this.findByIdOrThrowObjectNotFoundException(id);
    }

    @Override
    public List<VehicleResponse> findAll() {
       // logger.info("Listando tudo.");

        return vehicleRepository.findAll()
                .stream()
                .map(VehicleMapper::toResponse)
                .toList();
    }

    @Override
    public List<VehicleResponse> findAllByCostumer(Integer id) {
        // logger.info("Listando tudo.");

        return vehicleRepository.findAllByCostumer(id)
                .stream()
                .map(VehicleMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void update(VehicleRequestCreat request, Integer id) {

        existByPlate(request.plate());

        Vehicle byId = findByIdOrThrowObjectNotFoundException(id);

        Vehicle vehicleUpdate = VehicleMapper.toEntity(request, byId.getCostumer());

        vehicleUpdate.setId(id);

        vehicleRepository.saveAndFlush(vehicleUpdate);
    }

    @Override
    @Transactional
    public void delete(Integer id) {

        Vehicle byId = findByIdOrThrowObjectNotFoundException(id);

        vehicleRepository.delete(byId);
    }

    private Vehicle findByIdOrThrowObjectNotFoundException(Integer id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Vehicle not Found."));
    }

    private void existByPlate(String plate)  {
        if (vehicleRepository.existsByPlate(plate)) throw new BadRequestException("Vehicle is present");
    }
}
