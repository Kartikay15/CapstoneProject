package com.springboot.capstone_app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.capstone_app.model.User;
import com.springboot.capstone_app.repository.UserRepository;

@Service
public class UserSecurityService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{
		Optional<User> optional=userRepository.findByUsername(username);
		if(optional.isEmpty())
			throw new UsernameNotFoundException("Invalid username"); 
		User user = optional.get();
		return user;
	}
}

