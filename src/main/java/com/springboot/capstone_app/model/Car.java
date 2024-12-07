package com.springboot.capstone_app.model;

import com.springboot.capstone_app.enums.CarStatus;
import com.springboot.capstone_app.enums.Platform;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String car_model;
	private String purchase_year;
	private String fuel_type;
	private String body_type;
	private String car_condition;
	private int milage;
	private int price;
	private String brand;
	
	@Enumerated(EnumType.STRING)
	private CarStatus carStatus;
	
	@Enumerated(EnumType.STRING)
	private Platform platform;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCar_model() {
		return car_model;
	}

	public void setCar_model(String car_model) {
		this.car_model = car_model;
	}

	public String getPurchase_year() {
		return purchase_year;
	}

	public void setPurchase_year(String purchase_year) {
		this.purchase_year = purchase_year;
	}

	public String getFuel_type() {
		return fuel_type;
	}

	public void setFuel_type(String fuel_type) {
		this.fuel_type = fuel_type;
	}

	public String getBody_type() {
		return body_type;
	}

	public void setBody_type(String body_type) {
		this.body_type = body_type;
	}

	public String getCar_condition() {
		return car_condition;
	}

	public void setCar_condition(String car_condition) {
		this.car_condition = car_condition;
	}

	public int getMilage() {
		return milage;
	}

	public void setMilage(int milage) {
		this.milage = milage;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public CarStatus getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(CarStatus carStatus) {
		this.carStatus = carStatus;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

/*
 * @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String car_model;
	private String purchase_year;
	private String fuel_type;
	private String body_type;
	private String car_condition;
	private double price;
	
	@Enumerated(EnumType.STRING)
	private CarStatus carStatus;
	
	private String  verified;*/
	
	
	


	
	
	
} 