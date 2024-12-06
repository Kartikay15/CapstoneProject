package com.springboot.capstone_app.service;

import com.springboot.capstone_app.model.Feedback;
import com.springboot.capstone_app.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    // Create Feedback
    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    // Retrieve Feedback by ID
    public Optional<Feedback> getFeedbackById(Long id) {
        return feedbackRepository.findById(id);
    }

    // Retrieve All Feedbacks
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    // Update Feedback
    public Feedback updateFeedback(Long id, Feedback updatedFeedback) {
        Optional<Feedback> existingFeedback = feedbackRepository.findById(id);
        if (existingFeedback.isPresent()) {
            updatedFeedback.setFeedbackId(id); // Keep the same ID
            return feedbackRepository.save(updatedFeedback);
        } else {
            throw new RuntimeException("Feedback with ID " + id + " not found.");
        }
    }

    // Delete Feedback by ID
    public void deleteFeedback(Long id) {
        if (feedbackRepository.existsById(id)) {
            feedbackRepository.deleteById(id);
        } else {
            throw new RuntimeException("Feedback with ID " + id + " not found.");
        }
    }
}

