package com.springboot.capstone_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.capstone_app.model.Kpi;

public interface KpiRepository extends JpaRepository<Kpi, Integer> {
	Optional<Kpi> findByName(String name);
}
