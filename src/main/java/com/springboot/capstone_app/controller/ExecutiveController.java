package com.springboot.capstone_app.controller;

import com.springboot.capstone_app.model.Executive;
import com.springboot.capstone_app.enums.Department;
import com.springboot.capstone_app.enums.JobTitle;
import com.springboot.capstone_app.dto.ResponseMessageDto;
import com.springboot.capstone_app.exceptions.ResourceNotFoundException;
import com.springboot.capstone_app.service.ExecutiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class ExecutiveController {

	@Autowired
	private ExecutiveService executiveService;
	
	@PostMapping("/executive/add")
	public Executive addExecutive(@RequestBody Executive executive) {
		return executiveService.addExecutive(executive);
	}
	
	@GetMapping("/department/all")
	public List<Department> getAllDepartments() {
		return Arrays.asList(Department.values());
	}
	
	@GetMapping("/job-title/all")
	public List<JobTitle> getAllJobTitle() {
		return Arrays.asList(JobTitle.values());
	}
}