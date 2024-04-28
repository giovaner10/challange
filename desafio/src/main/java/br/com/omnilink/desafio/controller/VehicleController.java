package br.com.omnilink.desafio.controller;

import br.com.omnilink.desafio.DTO.request.vehicle.VehicleRequestCreat;
import br.com.omnilink.desafio.DTO.response.vehicle.VehicleResponse;
import br.com.omnilink.desafio.model.Vehicle;
import br.com.omnilink.desafio.service.VehicleService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody VehicleRequestCreat request) throws BadRequestException {
        vehicleService.save(request);
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
    public void update(@PathVariable Integer id,@Valid @RequestBody VehicleRequestCreat request) throws BadRequestException {
        vehicleService.update(request, id);
    }

    @GetMapping("/finbyid/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Cacheable("findByIdVehicle")
    public Vehicle findById(@PathVariable Integer id) throws BadRequestException {
        return vehicleService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id) throws BadRequestException {
        vehicleService.delete(id);
    }
}
