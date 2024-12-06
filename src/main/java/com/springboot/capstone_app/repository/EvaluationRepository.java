package com.springboot.capstone_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.capstone_app.model.Evaluation;

public interface EvaluationRepository extends JpaRepository<Evaluation, Integer> {
}
