package com.springboot.capstone_app.repository;


import com.springboot.capstone_app.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}

