package br.com.omnilink.desafio.service;

import br.com.omnilink.desafio.DTO.request.vehicle.VehicleRequestCreat;
import br.com.omnilink.desafio.DTO.response.vehicle.VehicleResponse;
import br.com.omnilink.desafio.exception.ObjectNotFoundException;
import br.com.omnilink.desafio.mapper.vehicle.VehicleMapper;
import br.com.omnilink.desafio.model.Costumer;
import br.com.omnilink.desafio.model.Vehicle;
import br.com.omnilink.desafio.repository.vehicle.VehicleRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    CostumerService costumerService;


    // private static final Logger logger = LoggerFactory.getLogger(VehicleService.class);
    public Vehicle findByIdOrThrowObjectNotFoundException(Integer id) throws BadRequestException {

        return vehicleRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Vehicle not Found."));
    }

    private void existByPlate(String plate) throws BadRequestException {
        if (vehicleRepository.existsByPlate(plate)) throw new BadRequestException("Vehicle is present");
    }

    public List<VehicleResponse> findAll() {
       // logger.info("Listando tudo.");

        return vehicleRepository.findAll()
                .stream()
                .map(VehicleMapper::toResponse)
                .toList();
    }

    @Transactional
    public void save(VehicleRequestCreat request) throws BadRequestException {

        existByPlate(request.plate());

        Costumer byId = costumerService.findByIdOrThrowObjectNotFoundException(request.costumerId());


        Vehicle vehicleSave = VehicleMapper.toEntity(request, byId);

        vehicleRepository.save(vehicleSave);
    }

    @Transactional
    public void update(VehicleRequestCreat request, Integer id) throws BadRequestException {

        existByPlate(request.plate());

        Vehicle byId = findByIdOrThrowObjectNotFoundException(id);

        Vehicle vehicleUpdate = VehicleMapper.toEntity(request, byId.getCostumer());

        vehicleUpdate.setId(id);

        vehicleRepository.saveAndFlush(vehicleUpdate);
    }

    @Transactional
    public void delete(Integer id) throws BadRequestException {

        Vehicle byId = findByIdOrThrowObjectNotFoundException(id);

        vehicleRepository.delete(byId);
    }
}
