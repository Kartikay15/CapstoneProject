package com.springboot.capstone_app.service;

import org.springframework.stereotype.Service;
import com.springboot.capstone_app.enums.CarType;

@Service
public class PriceService {

    // Calculate price based on CarType and number of days
    public int calculatePrice(CarType carType, int days) {
        if (carType == null || days <= 0) {
            throw new IllegalArgumentException("Invalid car type or days");
        }

        // Hardcoded realistic prices per day for each car type
        int pricePerDay;
        switch (carType) {
            case SUV:
                pricePerDay = 3000;
                break;
            case SEDAN:
                pricePerDay = 2500;
                break;
            case SPORTS:
                pricePerDay = 8000;
                break;
            case HATCHBACK:
                pricePerDay = 1500;
                break;
            case CONVERTIBLE:
                pricePerDay = 6000;
                break;
            default:
                throw new IllegalArgumentException("Unsupported car type");
        }

        return pricePerDay * days;
    }
}