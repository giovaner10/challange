package br.com.omnilink.desafio.repository.vehicle;

import br.com.omnilink.desafio.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
}
