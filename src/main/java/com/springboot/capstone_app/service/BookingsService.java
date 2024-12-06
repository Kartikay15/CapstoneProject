package com.springboot.capstone_app.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.capstone_app.enums.BookingStatus;
import com.springboot.capstone_app.model.Bookings;
import com.springboot.capstone_app.model.Car;
import com.springboot.capstone_app.repository.BookingsRepository;
import com.springboot.capstone_app.repository.CarRepository;

@Service
public class BookingsService {

    @Autowired
    private BookingsRepository bookingsRepository;

    @Autowired
    private CarRepository carRepository;

    // Create a booking
    public Bookings createBooking(int carId, String carType, int days, BookingStatus bookingStatus) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new IllegalArgumentException("Car not found"));

        // Calculate price
        int price;
        if (bookingStatus == BookingStatus.CANCELLED) {
            price = 500;
        } else {
            switch (carType.toUpperCase()) {
                case "SUV":
                    price = 3000 * days;
                    break;
                case "SEDAN":
                    price = 2500 * days;
                    break;
                case "LUXURY":
                    price = 6000 * days;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid car type");
            }
        }

        // Create and save the booking
        Bookings booking = new Bookings();
        booking.setCar(car);
        booking.setCarType(carType);
        booking.setDays(days);
        booking.setPrice(price);
        booking.setBookingStatus(bookingStatus);

        return bookingsRepository.save(booking);
    }
}
