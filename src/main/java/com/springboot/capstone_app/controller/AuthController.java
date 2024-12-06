package com.springboot.capstone_app.controller;

import com.springboot.capstone_app.dto.JwtDto;
import com.springboot.capstone_app.dto.ResponseMessageDto;
import com.springboot.capstone_app.exceptions.InvalidUsernameException;
import com.springboot.capstone_app.JwtUtil;
import com.springboot.capstone_app.model.User;
import com.springboot.capstone_app.service.UserSecurityService;
import com.springboot.capstone_app.service.UserService;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController 
@CrossOrigin(origins = {"http://localhost:4200"})
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private UserService userService;

    /**
     * Authenticate user and generate JWT token.
     */
    @PostMapping("/api/token")
    public ResponseEntity<?> getToken(@RequestBody User user) {
        JwtDto jwtDto = new JwtDto();
        try {
            // Authenticate user
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            // Load authenticated user details
            user = (User) userSecurityService.loadUserByUsername(user.getUsername());

            // Generate JWT token
            String jwt = jwtUtil.generateToken(user.getUsername());
            jwtDto.setUsername(user.getUsername());
            jwtDto.setToken(jwt);

            return ResponseEntity.ok(jwtDto);
        } catch (AuthenticationException ae) {
            return ResponseEntity.badRequest().body(ae.getMessage());
        }
    }

    /**
     * User registration (sign-up).
     */
    @PostMapping("/auth/sign-up")
    public ResponseEntity<?> signUp(@RequestBody User user, ResponseMessageDto dto) {
    	try {
			return ResponseEntity.ok(userService.signUp(user));
		} catch (InvalidUsernameException e) {
			 dto.setMsg(e.getMessage());
			 return ResponseEntity.badRequest().body(dto);
		}
    }

    /**
     * Test API to say hello (requires authentication).
     */
    @GetMapping("/api/hello")
    public String sayHello(Principal principal) {
        String user = (principal != null) ? principal.getName() : "TEMP_USER";
        return "API accessed by: " + user;
    }

    /**
     * API for job seekers (example endpoint, requires authentication).
     */
    @GetMapping("/api/job_seeker/hello")
    public String sayHelloExec(Principal principal) {
        String user = (principal != null) ? principal.getName() : "TEMP_USER";
        return "Job Seeker API accessed by: " + user;
    }

	@GetMapping("/auth/user")
	public User getUserDetails(Principal principal) {
		String loggedInUsername = principal.getName();
		User user  = (User)userSecurityService.loadUserByUsername(loggedInUsername);
		return user; 
	}
	
	@PostMapping("/auth/batch-sign-up")
    public ResponseEntity<?> batchSignUp(@RequestBody List<User> users, ResponseMessageDto dto) {
        try {
            List<User> savedUsers = userService.batchSignUp(users);
            return ResponseEntity.ok(savedUsers);
        } catch (Exception e) {
            dto.setMsg("Error processing batch sign-up: " + e.getMessage());
            return ResponseEntity.status(500).body(dto);
        }
    }
}
