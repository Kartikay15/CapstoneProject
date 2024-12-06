package com.springboot.capstone_app.model;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.springboot.capstone_app.enums.CustomerType;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long feedbackId;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private Long carId;

    @Column
    private Long evaluationId;

    @Column(columnDefinition = "TEXT")
    private String comments;

    @Column(nullable = false)
    private int rating; // 1 to 5

    @Column(nullable = false)
    private LocalDate feedbackDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CustomerType customerType;

    // Getters and Setters

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Long evaluationId) {
        this.evaluationId = evaluationId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDate getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(LocalDate feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }
}

