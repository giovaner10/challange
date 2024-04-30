package br.com.omnilink.desafio.service;

import br.com.omnilink.desafio.DTO.request.vehicle.VehicleRequestCreat;
import br.com.omnilink.desafio.DTO.response.vehicle.VehicleResponse;
import br.com.omnilink.desafio.exception.BadRequestException;
import br.com.omnilink.desafio.model.Vehicle;

import java.util.List;

public interface IVehicleService {

    Vehicle findById(Integer id) throws BadRequestException;

    List<VehicleResponse> findAll();

    List<VehicleResponse> findAllByCostumer(Integer id);

    void save(VehicleRequestCreat request) throws BadRequestException;

    void update(VehicleRequestCreat request, Integer id) throws BadRequestException;

    void delete(Integer id) throws BadRequestException;
}
