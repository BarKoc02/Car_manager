package pl.uken.Car_manager.controller;

import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pl.uken.Car_manager.model.Car;
import pl.uken.Car_manager.repository.CarRepository;

@RestController
public class CarController {

    @Autowired
    private CarRepository repository;

    @GetMapping("/manager")
    public List<Car> managerView()
    {
        List<Car> cars = repository.findAll();
        cars.sort(Comparator.comparing(Car::getName));
        return cars;
    }

    @PostMapping("/create_car")
    public String createCar(@RequestBody Car c)
    {
        repository.save(c);
        return "Car created successfully";
    }

    @DeleteMapping("/delete_car/{id}")
    public String deleteCar(@PathVariable("id") Long id)
    {
        if (id != null)
        {
            Car c = repository.findById(id).orElse(null);
            if (c != null)
            {
                repository.delete(c);
                return "Car deleted successfully";
            }
            return "Car not found";
        }
        return "Invalid ID";
    }

    @GetMapping("/edit/{id}")
    public Car editView(@PathVariable("id") Long id)
    {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/edit_car")
    public String editCar(@RequestBody Car c)
    {
        if (c != null)
        {
            Car existingCar = repository.findById(c.getId()).orElse(null);
            if (existingCar != null)
            {
                repository.save(c);
                return "Car updated successfully";
            }
            return "Car not found";
        }
        return "Invalid car data";
    }
}
