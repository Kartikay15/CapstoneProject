package com.springboot.capstone_app.repository;

import com.springboot.capstone_app.model.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpertRepository extends JpaRepository<Expert, Long> {

}
