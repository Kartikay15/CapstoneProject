package com.springboot.capstone_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.springboot.capstone_app.enums.CarStatus;
import com.springboot.capstone_app.enums.Platform;
import com.springboot.capstone_app.exceptions.ResourceNotFoundException;
import com.springboot.capstone_app.model.Car;
import com.springboot.capstone_app.repository.CarRepository;

//@Service
//public class CarService {
//
//    @Autowired
//    private CarRepository carRepository;
//
//    // Create or Update a Car
//    public Car saveOrUpdateCar(Car car) {
//        return carRepository.save(car);
//    }
//
//    // Retrieve all Cars
//    public List<Car> getAllCars() {
//        return carRepository.findAll();
//    }
//
//    // Retrieve a Car by ID
//    public Car getCarById(int id) {
//        return carRepository.findById(id).orElse(null);
//    }
//
//    // Delete a Car by ID
//    public void deleteCarById(int id) {
//        carRepository.deleteById(id);
//    }
//
//    // Get total number of cars
//    public long getTotalCars() {
//        return carRepository.count();
//    }
//
//    // Get number of cars available for rental
//    public long getCarsForRental() {
//        return carRepository.countByPlatform(Platform.RENTAL);
//    }
//
//    // Get number of cars available for resale
//    public long getCarsForResale() {
//        return carRepository.countByPlatform(Platform.RESELL);
//    }
//    
//    public Car updateCarPrice(int id, int newPrice) {
//        Car car = carRepository.findById(id).orElse(null);
//        if (car != null) {
//            car.setPrice(newPrice);
//            return carRepository.save(car);
//        }
//        return null; // Return null if car not found
//    }
//    
//    public Car updateCarStatus(int id, CarStatus newStatus) {
//        Car car = carRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Car not found with ID: " + id));
//        car.setCarStatus(newStatus);
//        return carRepository.save(car);
//    }
//    
//    public Car validate(int id) throws ResourceNotFoundException {
//		Optional<Car> optional = carRepository.findById(id);
//		if(optional.isEmpty())
//			throw new ResourceNotFoundException("Car id is invalid");
//		
//		Car car = optional.get();
//		return car; 
//		
//	}
//}
@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    // Save or Update a Car
    public Car saveOrUpdateCar(Car car) {
        return carRepository.save(car);
    }

    // Retrieve all Cars
//    public List<Car> getAllCars(Pageable pageable, String status) {
//        return carRepository.findAll(pageable).getContent();
//    }
    public List<Car> getAllCars(Pageable pageable, String status) throws ResourceNotFoundException {
     if ("all".equalsIgnoreCase(status)) {
         return carRepository.findAll(pageable).getContent();
     } else {
    	 try {
             // Convert the status string to the CarStatus enum
             CarStatus carStatus = CarStatus.valueOf(status.toUpperCase());
             return carRepository.findCarsByStatus(carStatus, pageable);
         } catch (IllegalArgumentException e) {
             throw new ResourceNotFoundException("Invalid car status: " + status);
         }
     }
  }
    
    // Validate Car by ID
    public Car validate(int id) throws ResourceNotFoundException {
        return carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found with ID: " + id));
    }

    // Delete a Car by ID
    public void deleteCarById(int id) throws ResourceNotFoundException {
        validate(id); // Ensure car exists before deletion
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

    // Update Car Price
    public Car updateCarPrice(int id, int newPrice) throws ResourceNotFoundException {
        Car car = validate(id);
        car.setPrice(newPrice);
        return carRepository.save(car);
    }

    // Update Car Status
    public Car updateCarStatus(int id, CarStatus newStatus) throws ResourceNotFoundException {
        Car car = validate(id);
        car.setCarStatus(newStatus);
        return carRepository.save(car);
    }
}

