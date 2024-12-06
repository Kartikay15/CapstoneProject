package com.springboot.capstone_app.controller;

import com.springboot.capstone_app.model.Log;
import com.springboot.capstone_app.service.LogService;
import com.springboot.capstone_app.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    // Create a new Log entry
    @PostMapping
    public ResponseEntity<Log> createLog(@RequestBody Log log) {
        Log createdLog = logService.createLog(log);
        return new ResponseEntity<>(createdLog, HttpStatus.CREATED);
    }

    // Retrieve a Log entry by ID
    @GetMapping("/{id}")
    public ResponseEntity<Log> getLogById(@PathVariable Long id) {
        try {
            Log log = logService.getLogById(id);
            return new ResponseEntity<>(log, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Retrieve all Logs
    @GetMapping
    public ResponseEntity<List<Log>> getAllLogs() {
        List<Log> logs = logService.getAllLogs();
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    // Update a Log entry
    @PutMapping("/{id}")
    public ResponseEntity<Log> updateLog(@PathVariable Long id, @RequestBody Log updatedLog) {
        try {
            Log log = logService.updateLog(id, updatedLog);
            return new ResponseEntity<>(log, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Delete a Log entry by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLog(@PathVariable Long id) {
        try {
            logService.deleteLog(id);
            return new ResponseEntity<>("Log deleted successfully", HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
