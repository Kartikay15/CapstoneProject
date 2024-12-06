package com.springboot.capstone_app.service;

import com.springboot.capstone_app.exceptions.ResourceNotFoundException;
import com.springboot.capstone_app.model.Log;
import com.springboot.capstone_app.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogService {

    private final LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    // Validate if the log exists by ID
    public Log validate(long id) throws ResourceNotFoundException {
        return logRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log entry not found with ID: " + id));
    }

    // Create a new Log entry
    public Log createLog(Log log) {
        // Set the timestamp to the current time if not provided
        if (log.getTimestamp() == null) {
            log.setTimestamp(java.time.LocalDateTime.now());
        }
        return logRepository.save(log);
    }

    // Retrieve a Log by ID
    public Log getLogById(Long id) throws ResourceNotFoundException {
        return logRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log entry not found with ID: " + id));
    }

    // Retrieve all Logs
    public List<Log> getAllLogs() {
        return logRepository.findAll();
    }

    // Update a Log entry
    public Log updateLog(Long id, Log updatedLog) throws ResourceNotFoundException {
        Log existingLog = validate(id); // Validate log entry existence
        existingLog.setAction(updatedLog.getAction());
        existingLog.setTimestamp(updatedLog.getTimestamp());
        existingLog.setDetails(updatedLog.getDetails());
        existingLog.setExecutive(updatedLog.getExecutive());
        return logRepository.save(existingLog);
    }

    // Delete a Log entry by ID
    public void deleteLog(Long id) throws ResourceNotFoundException {
        Log log = validate(id); // Validate log entry existence
        logRepository.delete(log);
    }
}
