package com.springboot.capstone_app.model;

import jakarta.persistence.*;


import com.springboot.capstone_app.enums.EvalStatus;
import com.springboot.capstone_app.enums.EvalVerdict;

@Entity
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @Enumerated(EnumType.STRING)
    private EvalStatus evalstatus; 
    
    @Enumerated(EnumType.STRING)
    private EvalVerdict evalVerdict;
    
    private int valuation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public EvalStatus getEvalstatus() {
		return evalstatus;
	}

	public void setEvalstatus(EvalStatus evalstatus) {
		this.evalstatus = evalstatus;
	}

	public EvalVerdict getEvalVerdict() {
		return evalVerdict;
	}

	public void setEvalVerdict(EvalVerdict evalVerdict) {
		this.evalVerdict = evalVerdict;
	}

	public int getValuation() {
		return valuation;
	}

	public void setValuation(int valuation) {
		this.valuation = valuation;
	}

	
}
