package com.springboot.capstone_app.repository;

import com.springboot.capstone_app.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
    // Additional query methods can be added here if needed
}
