package pl.uken.Car_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.uken.Car_manager.model.Car;

public interface CarRepository extends JpaRepository<Car, Long>{

    
}