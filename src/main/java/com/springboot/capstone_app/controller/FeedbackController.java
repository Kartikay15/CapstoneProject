package com.springboot.capstone_app.controller;

import com.springboot.capstone_app.model.Feedback;
import com.springboot.capstone_app.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    // Create Feedback
    @PostMapping
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback) {
        return ResponseEntity.ok(feedbackService.createFeedback(feedback));
    }

    // Retrieve Feedback by ID
    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long id) {
        return feedbackService.getFeedbackById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Retrieve All Feedbacks
    @GetMapping
    public List<Feedback> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    // Update Feedback
    @PutMapping("/{id}")
    public ResponseEntity<Feedback> updateFeedback(@PathVariable Long id, @RequestBody Feedback updatedFeedback) {
        return ResponseEntity.ok(feedbackService.updateFeedback(id, updatedFeedback));
    }

    // Delete Feedback
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.ok("Feedback deleted successfully.");
    }
}
