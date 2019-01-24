package ua.logos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.logos.domain.CarDTO;
import ua.logos.domain.SellerDTO;
import ua.logos.entity.CarEntity;
import ua.logos.entity.SellerEntity;
import ua.logos.exceptions.NotFoundException;
import ua.logos.repository.CarRepository;
import ua.logos.repository.SellerRepository;
import ua.logos.service.CarService;
import ua.logos.utils.ObjectMapperUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl  implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ObjectMapperUtils modelMapper;

    @Autowired
private  SellerRepository sellerRepository;

    @Override
    public void saveCar(CarDTO car) {
CarEntity carEntity = modelMapper.map(car,CarEntity.class);
        carRepository.save(carEntity);
    }

    @Override
    public List<CarDTO> findAllCars() {
        List<CarEntity>cars=carRepository.findAll();
         List<CarDTO>carDTOS=modelMapper.mapAll(cars,CarDTO.class);
        return carDTOS;
    }

    @Override
    public CarDTO findCarById(Long id) {
        boolean exists=carRepository.existsById(id);
        CarEntity car=carRepository.findById(id)
                .orElseThrow(
                        ()->  new NotFoundException("Car with id["+ id+"] not found"));

        CarDTO carDTO = modelMapper.map(car,CarDTO.class);
        return carDTO;
    }

    @Override
    public CarDTO updateCar(Long id, CarDTO carToUpdate) {
        boolean exists=carRepository.existsById(id);
        CarEntity carEntity=carRepository.findById(id)
               .orElseThrow(
                       ()-> new NotFoundException("Car with id ["+ id+"]not found"));

        CarEntity carFromDB = carRepository.findById(id).get();
        CarDTO carDTO = new CarDTO();
        carFromDB.setPrice(carToUpdate.getPrice());
        carFromDB.setColour_engine(carToUpdate.getColour_engine());
        carFromDB.setColour(carToUpdate.getColour());
        carFromDB.setVolume(carToUpdate.getVolume());
        carFromDB.setModel(carToUpdate.getModel());
        carFromDB.setBrand(carToUpdate.getBrand());


        SellerDTO sellerDTO = carToUpdate.getSeller();
      SellerDTO sellerDTO1 = new SellerDTO();
      sellerDTO1.setFirstName(sellerDTO.getFirstName());
      sellerDTO1.setLastName(sellerDTO.getLastName());
      sellerDTO1.setEmail(sellerDTO.getEmail());
      sellerDTO1.setNumberPhone(sellerDTO.getNumberPhone());
      sellerDTO1.setId(sellerDTO.getId());
      carDTO.setSeller(sellerDTO1);

        CarDTO carDTOs = modelMapper.map(carFromDB,CarDTO.class);
        carRepository.save(carFromDB);
        carDTOs.setId(carFromDB.getId());
        return  carDTOs;





    }

   /* private  CarDTO entityToDtoMapper(CarEntity carEntity){
        CarDTO carDTO= new CarDTO();
        carDTO.setId(carEntity.getId());
        carDTO.setBrand(carEntity.getBrand());
        carDTO.setModel(carEntity.getModel());
        carDTO.setVolume(carEntity.getVolume());
        carDTO.setColour(carEntity.getColour());
        carDTO.setColour_engine(carEntity.getColour_engine());
        carDTO.setPrice(carEntity.getPrice());

        SellerEntity sellerEntity=carEntity.getSeller();
        SellerDTO sellerDTO= new SellerDTO();
        sellerDTO.setId(sellerEntity.getId());
        sellerDTO.setFirstName(sellerEntity.getFirstName());
        sellerDTO.setLastName(sellerEntity.getLastName());
        sellerDTO.setNumberPhone(sellerEntity.getNumberPhone());
        sellerDTO.setEmail(sellerEntity.getEmail());
        carDTO.setSeller(sellerDTO);
        return  carDTO;
    }

    private  CarEntity dtoToEntityMapper(CarDTO carDTO){
        CarEntity carEntity= new CarEntity();
        carEntity.setId(carDTO.getId());
        carEntity.setBrand(carDTO.getBrand());
        carEntity.setModel(carDTO.getModel());
        carEntity.setVolume(carDTO.getVolume());
        carEntity.setColour(carDTO.getColour());
        carEntity.setColour_engine(carDTO.getColour_engine());
        carEntity.setPrice(carDTO.getPrice());


        SellerDTO sellerDTO= carDTO.getSeller();
        SellerEntity sellerEntity =new SellerEntity();
        sellerEntity.setFirstName(sellerDTO.getFirstName());
        sellerEntity.setLastName(sellerDTO.getLastName());
        sellerEntity.setNumberPhone(sellerDTO.getNumberPhone());
        sellerEntity.setEmail(sellerDTO.getEmail());


        carEntity.setSeller(sellerEntity);
        return  carEntity;
    }
*/

}
