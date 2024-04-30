package br.com.omnilink.challange.service;

import br.com.omnilink.challange.DTO.request.vehicle.VehicleRequestCreat;
import br.com.omnilink.challange.DTO.response.vehicle.VehicleResponse;
import br.com.omnilink.challange.exception.BadRequestException;
import br.com.omnilink.challange.model.Vehicle;

import java.util.List;

public interface IVehicleService {

    Vehicle findById(Integer id) throws BadRequestException;

    List<VehicleResponse> findAll();

    List<VehicleResponse> findAllByCostumer(Integer id);

    void save(VehicleRequestCreat request) throws BadRequestException;

    void update(VehicleRequestCreat request, Integer id) throws BadRequestException;

    void delete(Integer id) throws BadRequestException;
}
