package com.springboot.capstone_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.capstone_app.enums.BookingStatus;
import com.springboot.capstone_app.model.Bookings;
import com.springboot.capstone_app.service.BookingsService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/bookings")
public class BookingsController {

    @Autowired
    private BookingsService bookingsService;

    // Create booking
    @PostMapping("/car/{carId}")
    public ResponseEntity<Bookings> createBooking(
            @PathVariable int carId,
            @RequestParam String carType,
            @RequestParam int days,
            @RequestParam BookingStatus bookingStatus) {
        Bookings booking = bookingsService.createBooking(carId, carType, days, bookingStatus);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }
}
