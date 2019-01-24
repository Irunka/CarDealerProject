package ua.logos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.logos.domain.CarDTO;
import ua.logos.entity.CarEntity;
import ua.logos.service.CarService;

import java.util.List;

@RestController
@RequestMapping("cars")
public class CarController {

    @Autowired
   private  CarService carService;

    @PostMapping
    public ResponseEntity<?> createCar(
            @RequestBody CarDTO carDTO
            ){
        System.out.println(
                carDTO.getBrand()+" "+
                        carDTO.getModel()+""+
                        carDTO.getVolume()+" "+
                        carDTO.getColour()+" "+
                        carDTO.getColour_engine()+" "+
                        carDTO.getPrice()
        );
        carService.saveCar(carDTO);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

 @GetMapping
       public ResponseEntity<?>getCars(){
        List<CarDTO> cars=carService.findAllCars();
        return new ResponseEntity<>(cars,HttpStatus.OK);
}

      @GetMapping("{carId:[0-9]{1,5}}")
      public  ResponseEntity<?>getCarById(@PathVariable("carId")Long id){
        CarDTO cars= carService.findCarById(id);
        return  new ResponseEntity<>(HttpStatus.OK);
}


@PutMapping("{carId:[0-9]{1,5}}")
public  ResponseEntity<?>updateCar(
        @PathVariable("carId")Long id,@RequestBody CarDTO carToUpdate
){
CarDTO car=carService.updateCar(id,carToUpdate);

if (car==null){
    return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
}
return  new ResponseEntity<>(car,HttpStatus.OK);

}
}
