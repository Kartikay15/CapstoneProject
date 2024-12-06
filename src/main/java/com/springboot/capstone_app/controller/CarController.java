package com.springboot.capstone_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.capstone_app.model.Car;
import com.springboot.capstone_app.service.CarService;

@RestController
@RequestMapping("/cars")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CarController {

    @Autowired
    private CarService carService;

    // Create or Update Car
    @PostMapping
    public ResponseEntity<?> saveOrUpdateCar(@RequestBody Car car) {
        return new ResponseEntity<>(carService.saveOrUpdateCar(car), HttpStatus.CREATED);
    }

    // Get all Cars
    @GetMapping
    public ResponseEntity<?> getAllCars() {
        return new ResponseEntity<>(carService.getAllCars(), HttpStatus.OK);
    }

    // Get Car by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCarById(@PathVariable int id) {
        Car car = carService.getCarById(id);
        if (car != null) {
            return new ResponseEntity<>(car, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete Car by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarById(@PathVariable int id) {
        carService.deleteCarById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get total number of cars
    @GetMapping("/count")
    public ResponseEntity<Long> getTotalCars() {
        long totalCars = carService.getTotalCars();
        return new ResponseEntity<>(totalCars, HttpStatus.OK);
    }

    // Get number of cars available for rental
    @GetMapping("/count/rental")
    public ResponseEntity<Long> getCarsForRental() {
        long carsForRental = carService.getCarsForRental();
        return new ResponseEntity<>(carsForRental, HttpStatus.OK);
    }

    // Get number of cars available for resale
    @GetMapping("/count/resell")
    public ResponseEntity<Long> getCarsForResale() {
        long carsForResale = carService.getCarsForResale();
        return new ResponseEntity<>(carsForResale, HttpStatus.OK);
    }
    
    @PatchMapping("/{id}/price")
    public ResponseEntity<?> updateCarPrice(@PathVariable int id, @RequestParam int newPrice) {
        Car updatedCar = carService.updateCarPrice(id, newPrice);
        if (updatedCar != null) {
            return new ResponseEntity<>(updatedCar, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Car not found", HttpStatus.NOT_FOUND);
        }
    }
}
