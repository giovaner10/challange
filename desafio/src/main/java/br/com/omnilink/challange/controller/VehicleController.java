package br.com.omnilink.challange.controller;

import br.com.omnilink.challange.DTO.request.vehicle.VehicleRequest;
import br.com.omnilink.challange.DTO.response.vehicle.VehicleResponse;
import br.com.omnilink.challange.service.vehicle.IVehicleService;
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
    IVehicleService vehicleService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody VehicleRequest request) {
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
        return vehicleService.findAll();
    }

    @GetMapping("/findallbycostumer/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Cacheable("findAllVehicleByCostumer")
    public List<VehicleResponse> findAllByCostumer(@PathVariable Integer id) {
        return vehicleService.findAllByCostumer(id);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Integer id,@Valid @RequestBody VehicleRequest request) {
        vehicleService.update(request, id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id) {
        vehicleService.delete(id);
    }
}
