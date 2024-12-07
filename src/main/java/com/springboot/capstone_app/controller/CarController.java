package com.springboot.capstone_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.capstone_app.dto.ResponseMessageDto;
import com.springboot.capstone_app.enums.CarStatus;
import com.springboot.capstone_app.exceptions.ResourceNotFoundException;
import com.springboot.capstone_app.model.Car;
import com.springboot.capstone_app.service.CarService;

//@RestController
//@RequestMapping("/cars")
//@CrossOrigin(origins = {"http://localhost:4200"})
//public class CarController {
//
//    @Autowired
//    private CarService carService;
//
//    // Create or Update Car
//    @PostMapping
//    public ResponseEntity<?> saveOrUpdateCar(@RequestBody Car car) {
//        return new ResponseEntity<>(carService.saveOrUpdateCar(car), HttpStatus.CREATED);
//    }
//
//    // Get all Cars
//    @GetMapping
//    public ResponseEntity<?> getAllCars() {
//        return new ResponseEntity<>(carService.getAllCars(), HttpStatus.OK);
//    }
//
//    // Get Car by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getCarById(@PathVariable int id, ResponseMessageDto dto) {
//    	try {
//    		Car car = carService.validate(id);
//    		return ResponseEntity.ok(car);
//    	} catch (ResourceNotFoundException e) {
//    		dto.setMsg(e.getMessage());
//    		return ResponseEntity.badRequest().body(dto);
//    	}
//    	
//    	
////        Car car = carService.getCarById(id);
////        if (car != null) {
////            return new ResponseEntity<>(car, HttpStatus.OK);
////        } else {
////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////        }
//    }
//
//    // Get total number of cars
//    @GetMapping("/count")
//    public ResponseEntity<Long> getTotalCars() {
//        long totalCars = carService.getTotalCars();
//        return new ResponseEntity<>(totalCars, HttpStatus.OK);
//    }
//
//    // Get number of cars available for rental
//    @GetMapping("/count/rental")
//    public ResponseEntity<Long> getCarsForRental() {
//        long carsForRental = carService.getCarsForRental();
//        return new ResponseEntity<>(carsForRental, HttpStatus.OK);
//    }
//
//    // Get number of cars available for resale
//    @GetMapping("/count/resell")
//    public ResponseEntity<Long> getCarsForResale() {
//        long carsForResale = carService.getCarsForResale();
//        return new ResponseEntity<>(carsForResale, HttpStatus.OK);
//    }
//    
//    @PatchMapping("/{id}/price")
//    public ResponseEntity<?> updateCarPrice(@PathVariable int id, @RequestParam int newPrice) {
//        Car updatedCar = carService.updateCarPrice(id, newPrice);
//        if (updatedCar != null) {
//            return new ResponseEntity<>(updatedCar, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Car not found", HttpStatus.NOT_FOUND);
//        }
//    }
//    
//    @PatchMapping("/{id}/status")
//    public ResponseEntity<?> updateCarStatus(@PathVariable int id, @RequestParam CarStatus newStatus) {
//        Car updatedCar = carService.updateCarStatus(id, newStatus);
//        return ResponseEntity.ok(updatedCar);
//    }
//}

//**************************************************** 
// Working Fine


//@RestController
//@RequestMapping("/cars")
//@CrossOrigin(origins = {"http://localhost:4200"})
//public class CarController {
//
//    @Autowired
//    private CarService carService;
//
//    // Create or Update Car
//    @PostMapping
//    public ResponseEntity<?> saveOrUpdateCar(@RequestBody Car car) {
//        Car savedCar = carService.saveOrUpdateCar(car);
//        return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
//    }
//
//    // Get all Cars
//    @GetMapping
//    public ResponseEntity<List<Car>> getAllCars() {
//        return ResponseEntity.ok(carService.getAllCars());
//    }
//
//    // Get Car by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getCarById(@PathVariable int id, ResponseMessageDto dto) {
//        try {
//            Car car = carService.validate(id);
//            return ResponseEntity.ok(car);
//        } catch (ResourceNotFoundException e) {
//            dto.setMsg(e.getMessage());
//            return ResponseEntity.badRequest().body(dto);
//        }
//    }
//
//    // Delete Car by ID
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteCarById(@PathVariable int id, ResponseMessageDto dto) {
//        try {
//            carService.deleteCarById(id);
//            dto.setMsg("Car deleted successfully.");
//            return ResponseEntity.ok(dto);
//        } catch (ResourceNotFoundException e) {
//            dto.setMsg(e.getMessage());
//            return ResponseEntity.badRequest().body(dto);
//        }
//    }
//
//    // Get total number of cars
//    @GetMapping("/count")
//    public ResponseEntity<Long> getTotalCars() {
//        return ResponseEntity.ok(carService.getTotalCars());
//    }
//
//    // Get number of cars available for rental
//    @GetMapping("/count/rental")
//    public ResponseEntity<Long> getCarsForRental() {
//        return ResponseEntity.ok(carService.getCarsForRental());
//    }
//
//    // Get number of cars available for resale
//    @GetMapping("/count/resell")
//    public ResponseEntity<Long> getCarsForResale() {
//        return ResponseEntity.ok(carService.getCarsForResale());
//    }
//
//    // Update Car Price
//    @PatchMapping("/{id}/price")
//    public ResponseEntity<?> updateCarPrice(@PathVariable int id, @RequestParam int newPrice, ResponseMessageDto dto) {
//        try {
//            Car updatedCar = carService.updateCarPrice(id, newPrice);
//            return ResponseEntity.ok(updatedCar);
//        } catch (ResourceNotFoundException e) {
//            dto.setMsg(e.getMessage());
//            return ResponseEntity.badRequest().body(dto);
//        }
//    }
//
//    // Update Car Status
//    @PatchMapping("/{id}/status")
//    public ResponseEntity<?> updateCarStatus(@PathVariable int id, @RequestParam CarStatus newStatus, ResponseMessageDto dto) {
//        try {
//            Car updatedCar = carService.updateCarStatus(id, newStatus);
//            return ResponseEntity.ok(updatedCar);
//        } catch (ResourceNotFoundException e) {
//            dto.setMsg(e.getMessage());
//            return ResponseEntity.badRequest().body(dto);
//        }
//    }
//}




//*******************************************************



@RestController
@RequestMapping("/cars")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CarController {

    @Autowired
    private CarService carService;

    // Create or Update Car
    @PostMapping
    public ResponseEntity<?> saveOrUpdateCar(@RequestBody Car car) {
        Car savedCar = carService.saveOrUpdateCar(car);
        return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
    }

    // Get all Cars
    @GetMapping
    public ResponseEntity<List<Car>> getAllCars(
    		@RequestParam(required = false, defaultValue = "0") String page, 
    		@RequestParam(required = false, defaultValue = "100000") String size,
    		@RequestParam(required = false, defaultValue = "all") String status) throws ResourceNotFoundException {
    	
    	Pageable pageable = null;
    	
    	try {
    		pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
    	} catch(Exception e) {
    		e.getMessage();
    	}
    	List<Car> list = carService.getAllCars(pageable,status);
        return ResponseEntity.ok(list);
    }

    // Get Car by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCarById(@PathVariable int id, ResponseMessageDto dto) {
        try {
            Car car = carService.validate(id);
            return ResponseEntity.ok(car);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }

    // Delete Car by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarById(@PathVariable int id, ResponseMessageDto dto) {
        try {
            carService.deleteCarById(id);
            dto.setMsg("Car deleted successfully.");
            return ResponseEntity.ok(dto);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }

    // Get total number of cars
    @GetMapping("/count")
    public ResponseEntity<Long> getTotalCars() {
        return ResponseEntity.ok(carService.getTotalCars());
    }

    // Get number of cars available for rental
    @GetMapping("/count/rental")
    public ResponseEntity<Long> getCarsForRental() {
        return ResponseEntity.ok(carService.getCarsForRental());
    }

    // Get number of cars available for resale
    @GetMapping("/count/resell")
    public ResponseEntity<Long> getCarsForResale() {
        return ResponseEntity.ok(carService.getCarsForResale());
    }

    // Update Car Price
    @PatchMapping("/{id}/price")
    public ResponseEntity<?> updateCarPrice(@PathVariable int id, @RequestParam int newPrice, ResponseMessageDto dto) {
        try {
            Car updatedCar = carService.updateCarPrice(id, newPrice);
            return ResponseEntity.ok(updatedCar);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }

    // Update Car Status
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateCarStatus(@PathVariable int id, @RequestParam CarStatus newStatus, ResponseMessageDto dto) {
        try {
            Car updatedCar = carService.updateCarStatus(id, newStatus);
            return ResponseEntity.ok(updatedCar);
        } catch (ResourceNotFoundException e) {
            dto.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(dto);
        }
    }
}































