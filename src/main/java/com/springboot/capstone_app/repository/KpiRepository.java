package com.springboot.capstone_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.capstone_app.model.Kpi;

public interface KpiRepository extends JpaRepository<Kpi, Integer> {
	Optional<Kpi> findByName(String name);
	
	// Compute sum of valuation for Cars with status SOLD
    @Query(value = "SELECT COALESCE(SUM(price), 0) FROM car WHERE car_status = 'SOLD'", nativeQuery = true)
    int getRevResell();

    // Compute sum of price in RentalPriceTable
    @Query(value = "SELECT COALESCE(SUM(price), 0) FROM rental_price", nativeQuery = true)
    int getRevRental();

    // Count Bookings with status CANCELLED
    @Query(value = "SELECT COUNT(*) FROM bookings WHERE booking_status = 'CANCELLED'", nativeQuery = true)
    int getCancelBookings();

    // Count Bookings with status HIRED
    @Query(value = "SELECT COUNT(*) FROM bookings WHERE booking_status = 'HIRED'", nativeQuery = true)
    int getHiredBookings();
    
 // Count the total number of rentals in the rental_price table
    @Query(value = "SELECT COUNT(*) FROM car WHERE platform = 'RENTAL'", nativeQuery = true)
    int getTotalRentals();
    
    @Query(value = "SELECT COUNT(*) FROM car WHERE car_status = 'SOLD'", nativeQuery = true)
    int getCarsSold();
    
    @Query(value = "SELECT COUNT(*) FROM user_info", nativeQuery = true)
    int getUsers();
    
    @Query(value = "SELECT COUNT(*) FROM car WHERE car_status = 'INVENTORY'", nativeQuery = true)
    int getResellInventory();
    
    @Query(value = "SELECT COUNT(*) FROM car WHERE car_status = 'LISTED'", nativeQuery = true)
    int getListedCars();
}
