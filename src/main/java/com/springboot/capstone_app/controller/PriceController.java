package com.springboot.capstone_app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.capstone_app.enums.CarType;
import com.springboot.capstone_app.service.PriceService;

@RestController
@RequestMapping("/price")
@CrossOrigin(origins = {"http://localhost:4200"})
public class PriceController {

    @Autowired
    private PriceService priceService;

    // Calculate price based on car type and days
    @GetMapping("/calculate")
    public ResponseEntity<Integer> calculatePrice(
            @RequestParam CarType carType,
            @RequestParam int days) {
        int price = priceService.calculatePrice(carType, days);
        return ResponseEntity.ok(price);
    }
}