package br.com.omnilink.challange.controller;

import br.com.omnilink.challange.DTO.request.vehicle.VehicleRequestCreat;
import br.com.omnilink.challange.DTO.response.vehicle.VehicleResponse;
import br.com.omnilink.challange.model.Vehicle;
import br.com.omnilink.challange.service.VehicleServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    VehicleServiceImpl vehicleService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody VehicleRequestCreat request) throws JsonProcessingException {
        vehicleService.save(request);
    }

    @GetMapping("/finbyid/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Cacheable("findByIdVehicle")
    public VehicleResponse findById(@PathVariable Integer id) {
        return vehicleService.findById(id);
    }

    @GetMapping("/findall")
    @ResponseStatus(HttpStatus.OK)
    @Cacheable("findAllVehicle")
    public List<VehicleResponse> findAll() {
        //logger.info("Listando tudo!");
        return vehicleService.findAll();
    }

    @GetMapping("/findallByCostumer/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Cacheable("findAllVehicleByCostumer")
    public List<VehicleResponse> findAllByCostumer(@PathVariable Integer id) {
        //logger.info("Listando tudo!");
        return vehicleService.findAllByCostumer(id);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Integer id,@Valid @RequestBody VehicleRequestCreat request) {
        vehicleService.update(request, id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id) {
        vehicleService.delete(id);
    }
}
