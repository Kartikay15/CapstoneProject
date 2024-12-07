package com.springboot.capstone_app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.capstone_app.enums.CarStatus;
import com.springboot.capstone_app.enums.Platform;
import com.springboot.capstone_app.model.Car;
import com.springboot.capstone_app.model.Kpi;

public interface CarRepository extends JpaRepository<Car, Integer>{
	

	@Query("SELECT COUNT(c) FROM Car c WHERE c.platform = :platform")
	long countByPlatform(@Param("platform") Platform platform);

	 @Query("SELECT c FROM Car c WHERE c.carStatus = :status")
	    List<Car> findCarsByStatus(@Param("status") CarStatus status, Pageable pageable);
}
