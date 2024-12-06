package com.springboot.capstone_app.service;

import com.springboot.capstone_app.enums.Role;
import com.springboot.capstone_app.model.User;
import com.springboot.capstone_app.repository.UserRepository;
import com.springboot.capstone_app.exceptions.ResourceNotFoundException;
import com.springboot.capstone_app.model.Executive;

import com.springboot.capstone_app.repository.ExecutiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExecutiveService {

	@Autowired
	private ExecutiveRepository executiveRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	public Executive addExecutive(Executive executive) {
		/*detach user from given executive */
		User user = executive.getUser();
		user.setRole(Role.EXECUTIVE);
		
		String encPassword = passwordEncoder.encode(user.getPassword());
		 
		user.setPassword(encPassword);
		user = userRepository.save(user); //complete user with role, password and id
		
		executive.setUser(user);
		
		return executiveRepository.save(executive);
	}
	// Validate that the expert exists
    public Executive validate(int id) throws ResourceNotFoundException {
        Optional<Executive> executive = executiveRepository.findById(id);
        if (executive.isEmpty()) {
            throw new ResourceNotFoundException("Expert not found with ID: " + id);
        }
        return executive.get();
    }
    


    
    
    

}

