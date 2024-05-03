package br.com.omnilink.challange.service.vehicle;

import br.com.omnilink.challange.DTO.request.vehicle.VehicleRequest;
import br.com.omnilink.challange.DTO.response.vehicle.VehicleResponse;

import java.util.List;

public interface IVehicleService {

    VehicleResponse findById(Integer id);

    List<VehicleResponse> findAll();

    List<VehicleResponse> findAllByCostumer(Integer id);

    void save(VehicleRequest request);

    void update(VehicleRequest request, Integer id);

    void delete(Integer id);
}
