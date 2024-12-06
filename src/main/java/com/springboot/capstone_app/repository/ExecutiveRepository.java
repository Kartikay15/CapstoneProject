package com.springboot.capstone_app.repository;

import com.springboot.capstone_app.model.Executive;
import com.springboot.capstone_app.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExecutiveRepository extends JpaRepository<Executive, Integer> {
	//Optional<Executive> findById(int id);
}
