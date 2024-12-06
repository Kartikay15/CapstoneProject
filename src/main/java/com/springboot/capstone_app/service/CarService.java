package com.springboot.capstone_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.capstone_app.enums.Platform;
import com.springboot.capstone_app.model.Car;
import com.springboot.capstone_app.repository.CarRepository;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    // Create or Update a Car
    public Car saveOrUpdateCar(Car car) {
        return carRepository.save(car);
    }

    // Retrieve all Cars
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    // Retrieve a Car by ID
    public Car getCarById(int id) {
        return carRepository.findById(id).orElse(null);
    }

    // Delete a Car by ID
    public void deleteCarById(int id) {
        carRepository.deleteById(id);
    }

    // Get total number of cars
    public long getTotalCars() {
        return carRepository.count();
    }

    // Get number of cars available for rental
    public long getCarsForRental() {
        return carRepository.countByPlatform(Platform.RENTAL);
    }

    // Get number of cars available for resale
    public long getCarsForResale() {
        return carRepository.countByPlatform(Platform.RESELL);
    }
    
    public Car updateCarPrice(int id, int newPrice) {
        Car car = carRepository.findById(id).orElse(null);
        if (car != null) {
            car.setPrice(newPrice);
            return carRepository.save(car);
        }
        return null; // Return null if car not found
    }
}
