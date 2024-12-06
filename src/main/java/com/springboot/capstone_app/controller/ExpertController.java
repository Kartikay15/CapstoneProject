package com.springboot.capstone_app.controller;

import com.springboot.capstone_app.model.Expert;
import com.springboot.capstone_app.service.ExpertService;
import com.springboot.capstone_app.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experts")
public class ExpertController {

    private final ExpertService expertService;

    @Autowired
    public ExpertController(ExpertService expertService) {
        this.expertService = expertService;
    }

    // Create a new Expert
    @PostMapping
    public ResponseEntity<Expert> createExpert(@RequestBody Expert expert) {
        try {
            Expert createdExpert = expertService.createExpert(expert);
            return new ResponseEntity<>(createdExpert, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);  // Invalid Executive
        }
    }

    // Retrieve an Expert by ID
    @GetMapping("/{id}")
    public ResponseEntity<Expert> getExpertById(@PathVariable Long id) {
        try {
            Expert expert = expertService.getExpertById(id);
            return new ResponseEntity<>(expert, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Retrieve all Experts
    @GetMapping
    public ResponseEntity<List<Expert>> getAllExperts() {
        List<Expert> experts = expertService.getAllExperts();
        return new ResponseEntity<>(experts, HttpStatus.OK);
    }

    // Update an Expert by ID
    @PutMapping("/{id}")
    public ResponseEntity<Expert> updateExpert(@PathVariable Long id, @RequestBody Expert expert) {
        try {
            Expert updatedExpert = expertService.updateExpert(id, expert);
            return new ResponseEntity<>(updatedExpert, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Delete an Expert by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpert(@PathVariable Long id) {
        try {
            expertService.deleteExpert(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
