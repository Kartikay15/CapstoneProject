package com.springboot.capstone_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import java.time.LocalDate;

@Entity
public class Kpi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int kpiID;

    
    private String name;
    private int value;
    
	public int getKpiID() {
		return kpiID;
	}
	
	public void setKpiID(int kpiID) {
		this.kpiID = kpiID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}   
}