package com.springboot.capstone_app.model;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.springboot.capstone_app.enums.Status;

@Entity
public class Expert {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long expertID;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String contactInfo;

    @ManyToOne
    @JoinColumn(name = "assignedBy")
    private Executive assignedBy;  // Many-to-one relationship with Executive

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.FREE;

//    @OneToOne
//    @JoinColumn(name = "assignedToEvaluationID", unique = true)
//    private Evaluation assignedEvaluation;

    // Getters and Setters

    public Long getExpertID() {
        return expertID;
    }

    public void setExpertID(Long expertID) {
        this.expertID = expertID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

//    public Evaluation getAssignedEvaluation() {
//        return assignedEvaluation;
//    }
//
//    public void setAssignedEvaluation(Evaluation assignedEvaluation) {
//        this.assignedEvaluation = assignedEvaluation;
//        this.status = (assignedEvaluation == null) ? Status.FREE : Status.ASSIGNED;
//    }

    public Executive getAssignedBy() {
        return assignedBy;  // Getter for the assignedBy field (Executive)
    }

    public void setAssignedBy(Executive assignedBy) {
        this.assignedBy = assignedBy;
    }

//    @Override
//    public String toString() {
//        return "Expert{" +
//                "expertID=" + expertID +
//                ", name='" + name + '\'' +
//                ", contactInfo='" + contactInfo + '\'' +
//                ", status=" + status +
//                ", assignedEvaluationID=" + (assignedEvaluation != null ? assignedEvaluation.getEvaluationID() : "null") +
//                '}';
//    }
}
