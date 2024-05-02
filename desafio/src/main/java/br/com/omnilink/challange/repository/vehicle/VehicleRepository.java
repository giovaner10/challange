package br.com.omnilink.challange.repository.vehicle;

import br.com.omnilink.challange.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    boolean existsByPlate(String plate);

    boolean existsByPlateAndId(String plate, Integer id);

    @Query("SELECT v FROM Vehicle v WHERE v.costumer.id = :id")
    List<Vehicle> findAllByCostumer(Integer id);
}
