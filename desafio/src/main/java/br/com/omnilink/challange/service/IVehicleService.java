package br.com.omnilink.challange.service;

import br.com.omnilink.challange.DTO.request.vehicle.VehicleRequestCreat;
import br.com.omnilink.challange.DTO.response.vehicle.VehicleResponse;
import br.com.omnilink.challange.exception.BadRequestException;
import br.com.omnilink.challange.model.Vehicle;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IVehicleService {

    VehicleResponse findById(Integer id) throws BadRequestException;

    List<VehicleResponse> findAll();

    List<VehicleResponse> findAllByCostumer(Integer id);

    void save(VehicleRequestCreat request) throws BadRequestException, JsonProcessingException;

    void update(VehicleRequestCreat request, Integer id) throws BadRequestException;

    void delete(Integer id) throws BadRequestException;
}
