package ua.logos.service;


import ua.logos.domain.CarDTO;
import ua.logos.entity.CarEntity;

import java.util.List;

public interface CarService {

    void saveCar(CarDTO car);

    List<CarDTO> findAllCars();

    CarDTO findCarById(Long id);

CarDTO updateCar(Long id,CarDTO carToUpdate);
}
