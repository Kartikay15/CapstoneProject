package com.springboot.capstone_app.service;

import com.springboot.capstone_app.enums.Status;
import com.springboot.capstone_app.exceptions.ResourceNotFoundException;
import com.springboot.capstone_app.model.Expert;
import com.springboot.capstone_app.repository.ExpertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpertService {

    private final ExpertRepository expertRepository;
    private final ExecutiveService executiveService;  // To validate Executive existence

    @Autowired
    public ExpertService(ExpertRepository expertRepository, ExecutiveService executiveService) {
        this.expertRepository = expertRepository;
        this.executiveService = executiveService;
    }

    // Validate that the expert exists
    public Expert validate(long id) throws ResourceNotFoundException {
        Optional<Expert> expert = expertRepository.findById(id);
        if (expert.isEmpty()) {
            throw new ResourceNotFoundException("Expert not found with ID: " + id);
        }
        return expert.get();
    }

    // Create a new Expert
    public Expert createExpert(Expert expert) throws ResourceNotFoundException {
        // Initially, the assignedBy can be null and status will be FREE
        if (expert.getAssignedBy() != null) {
            // If assignedBy is not null, validate the Executive
            executiveService.validate(expert.getAssignedBy().getId());
            expert.setStatus(expert.getStatus() != null ? expert.getStatus() : Status.ASSIGNED); // Default to ASSIGNED if not set
        } else {
            expert.setStatus(Status.FREE); // If no assignedBy, set status to FREE
        }
        
        return expertRepository.save(expert);
    }

    // Retrieve an Expert by ID
    public Expert getExpertById(Long id) throws ResourceNotFoundException {
        return validate(id);  // Reuses validate method to throw exception if not found
    }

    // Retrieve all Experts
    public List<Expert> getAllExperts() {
        return expertRepository.findAll();
    }

    // Update an Expert
    public Expert updateExpert(Long id, Expert updatedExpert) throws ResourceNotFoundException {
        Expert existingExpert = validate(id);  // Ensure the Expert exists before updating
        existingExpert.setName(updatedExpert.getName());
        existingExpert.setContactInfo(updatedExpert.getContactInfo());
        existingExpert.setStatus(updatedExpert.getStatus());
       // existingExpert.setAssignedEvaluation(updatedExpert.getAssignedEvaluation());
        existingExpert.setAssignedBy(updatedExpert.getAssignedBy());

        // Update status based on whether assignedBy is null or not
        if (updatedExpert.getAssignedBy() != null) {
            executiveService.validate(updatedExpert.getAssignedBy().getId()); // Validate if assignedBy is provided
            existingExpert.setStatus(Status.ASSIGNED);
        } else {
            existingExpert.setStatus(Status.FREE); // If assignedBy is null, set to FREE
        }

        return expertRepository.save(existingExpert);
    }

    // Delete an Expert by ID
    public void deleteExpert(Long id) throws ResourceNotFoundException {
        Expert expert = validate(id);  // Ensure the Expert exists before deleting
        expertRepository.delete(expert);
    }
}
