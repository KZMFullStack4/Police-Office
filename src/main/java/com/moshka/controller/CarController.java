package com.moshka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moshka.dao.CarRepository;
import com.moshka.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping("/get-car")
    public ResponseEntity<Car> getCar(@RequestHeader String model) throws JsonProcessingException {
        System.out.println( " *************** In Request ");
        System.out.println(" Header : " +model );
        if(carRepository==null){
            throw new NullPointerException(" Car Repository is null ");
        }
        Object myCar =carRepository.findByModel(model);
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(" Car loaded " +mapper.writeValueAsString(myCar));
//        System.out.println( " Founded car : " +myCar.getName());

//        return new  ResponseEntity<Object>(myCar,null,HttpStatus.OK);
    }
}
