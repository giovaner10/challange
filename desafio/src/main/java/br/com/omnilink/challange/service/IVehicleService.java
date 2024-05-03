package br.com.omnilink.challange.service;

import br.com.omnilink.challange.DTO.request.vehicle.VehicleRequestCreat;
import br.com.omnilink.challange.DTO.response.vehicle.VehicleResponse;

import java.util.List;

public interface IVehicleService {

    VehicleResponse findById(Integer id);

    List<VehicleResponse> findAll();

    List<VehicleResponse> findAllByCostumer(Integer id);

    void save(VehicleRequestCreat request);

    void update(VehicleRequestCreat request, Integer id);

    void delete(Integer id);
}
