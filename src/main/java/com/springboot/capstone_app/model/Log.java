package com.springboot.capstone_app.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logID;

    @ManyToOne
    @JoinColumn(name = "executiveID", nullable = false)
    private Executive executive; // The executive who performed the action

    @Column(nullable = false)
    private String action; // Action performed by the executive (e.g., "Created KPI", "Updated Expert")

    @Column(nullable = false)
    private LocalDateTime timestamp; // Timestamp of when the action was performed

    @Column(nullable = true)
    private String details; // Optional field for additional details about the action (can be null)

    // Getters and Setters

    public Long getLogID() {
        return logID;
    }

    public void setLogID(Long logID) {
        this.logID = logID;
    }

    public Executive getExecutive() {
        return executive;
    }

    public void setExecutive(Executive executive) {
        this.executive = executive;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Log{" +
                "logID=" + logID +
                ", executive=" + (executive != null ? executive.getId() : null) +
                ", action='" + action + '\'' +
                ", timestamp=" + timestamp +
                ", details='" + details + '\'' +
                '}';
    }
}
