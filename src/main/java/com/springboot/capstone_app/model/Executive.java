package com.springboot.capstone_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import com.springboot.capstone_app.enums.Department;
import com.springboot.capstone_app.enums.JobTitle;
import com.springboot.capstone_app.model.User;
import com.springboot.capstone_app.enums.Role;

import jakarta.persistence.Column;

@Entity
public class Executive {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name; 
	
	private String contact; 
	
	@Enumerated(EnumType.STRING)
	private Department department; 
	
	@Enumerated(EnumType.STRING)
	private JobTitle jobTitle; 
	
	@OneToOne
	private User user;
    /*
     name 
     contact
     department
     jobTitle
     user:{
     	username:
     	password: 
     }
     * */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public JobTitle getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(JobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	} 
	
	
}