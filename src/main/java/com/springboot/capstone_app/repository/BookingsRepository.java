package com.springboot.capstone_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.capstone_app.model.Bookings;

public interface BookingsRepository extends JpaRepository<Bookings, Integer> {
}
