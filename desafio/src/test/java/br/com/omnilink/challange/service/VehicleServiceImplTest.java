package br.com.omnilink.challange.service;

import br.com.omnilink.challange.DTO.request.vehicle.VehicleRequestCreat;
import br.com.omnilink.challange.DTO.response.vehicle.VehicleResponse;
import br.com.omnilink.challange.exception.BadRequestException;
import br.com.omnilink.challange.exception.ObjectNotFoundException;
import br.com.omnilink.challange.factory.VehicleFactory;
import br.com.omnilink.challange.model.Vehicle;
import br.com.omnilink.challange.repository.vehicle.VehicleRepository;
import br.com.omnilink.challange.security.security.UserDetailsLogged;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class VehicleServiceImplTest {


    @InjectMocks
    private VehicleServiceImpl vehicleService;

    @Mock
    ICostumerService costumerService;

    @Mock
    VehicleRepository vehicleRepository;

    @Mock
    UserDetailsLogged logged;


    Vehicle vehicle = null;
    VehicleResponse vehicleResponse = null;
    VehicleRequestCreat vehicleRequest = null;

    @BeforeEach
    void setUp() {
        vehicle = VehicleFactory.getVehicle();
        vehicleResponse = VehicleFactory.getResponse();
        vehicleRequest = VehicleFactory.getRequest();
    }

    @Test
    void save() {
        when(vehicleRepository.existsByPlate("ABC123")).thenReturn(false);

        when(costumerService.findByIdOrThrowObjectNotFoundException(1)).thenReturn(vehicle.getCostumer());

        assertDoesNotThrow(() -> vehicleService.save(vehicleRequest));
    }

    @Test
    void dontSaveExistsPlate() {
        when(vehicleRepository.existsByPlate("ABC123")).thenReturn(true);

        assertThrows(BadRequestException.class, () -> vehicleService.save(vehicleRequest));
    }

    @Test
    void dontSaveNotExistsCostumer() {
        when(vehicleRepository.existsByPlate("ABC123")).thenReturn(false);

        when(costumerService.findByIdOrThrowObjectNotFoundException(1)).thenThrow(ObjectNotFoundException.class);

        assertThrows(ObjectNotFoundException.class, () -> vehicleService.save(vehicleRequest));
    }


    @Test
    void findAll() {
        when(vehicleRepository.findAll()).thenReturn(List.of(vehicle));

        List<VehicleResponse> all = vehicleService.findAll();

        assertFalse(all.isEmpty());
    }

    @Test
    void findAllByCostumer() {
        when(vehicleRepository.findAllByCostumer(1)).thenReturn(List.of(vehicle));

        List<VehicleResponse> all = vehicleService.findAllByCostumer(1);

        assertFalse(all.isEmpty());
    }


    @Test
    void findById() {
        when(vehicleRepository.findById(1)).thenReturn(Optional.ofNullable(vehicle));

        VehicleResponse byId = vehicleService.findById(1);

        assertEquals(vehicleResponse.plate(), byId.plate());
    }

    @Test
    void findByIdDontExists() {
        when(vehicleRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> vehicleService.findById(1));
    }

    @Test
    void delete() {
        when(vehicleRepository.findById(1)).thenReturn(Optional.ofNullable(vehicle));

        assertDoesNotThrow(() -> vehicleService.delete(1));
    }

    @Test
    void update() {
        when(vehicleRepository.existsByPlateAndId("ABC123", 1)).thenReturn(true);

        assertDoesNotThrow(() -> vehicleService.update(vehicleRequest, 1));
    }

    @Test
    void dontUpdateExistingPlate() {
        when(vehicleRepository.existsByPlateAndId("ABC123", 1)).thenReturn(false);

        assertThrows(BadRequestException.class, () -> vehicleService.update(vehicleRequest, 1));
    }
}