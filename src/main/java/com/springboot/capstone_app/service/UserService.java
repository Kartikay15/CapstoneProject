package com.springboot.capstone_app.service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.capstone_app.enums.Converted;
import com.springboot.capstone_app.enums.Role;
import com.springboot.capstone_app.exceptions.InvalidUsernameException;
import com.springboot.capstone_app.model.User;
import com.springboot.capstone_app.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passEncoder;
	
	private LocalDateTime time;
	
	public User signUp(User user) throws InvalidUsernameException {
		Optional<User> optional = userRepository.findByUsername(user.getUsername());
		if(optional.isPresent()) {
			throw new InvalidUsernameException("Username already in use");
		}
        // Assign default role if not provided
		System.out.println(user.getRole());
		if(user.getRole() == null)
		{
			user.setRole(Role.EXECUTIVE);
		}
		else user.setRole(user.getRole());
		
		
		
		
		
		//encrypt the password 
		String encryptedPass = passEncoder.encode(user.getPassword());
		user.setPassword(encryptedPass);
		
		user.setUsername(user.getUsername());
		user.setName(user.getName());
		
		System.out.println(user.getRole());
		
		return userRepository.save(user);
	}
	
	 public List<User> batchSignUp(List<User> users) {
	        List<User> savedUsers = new ArrayList<>();
	        for (User user : users) {
	            try {
	                User savedUser = signUp(user);
	                savedUsers.add(savedUser);
	            } catch (InvalidUsernameException e) {
	                // Log the error and skip this user
	                System.err.println("Error saving user: " + user.getUsername() + " - " + e.getMessage());
	            }
	        }
	        return savedUsers;
	    }
}
