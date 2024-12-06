package com.springboot.capstone_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.capstone_app.enums.Platform;
import com.springboot.capstone_app.model.Car;

public interface CarRepository extends JpaRepository<Car, Integer>{
	

	@Query("SELECT COUNT(c) FROM Car c WHERE c.platform = :platform")
	long countByPlatform(@Param("platform") Platform platform);
}
