package com.springboot.capstone_app.controller;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.capstone_app.enums.Converted;
import com.springboot.capstone_app.enums.Role;
import com.springboot.capstone_app.model.User;
import com.springboot.capstone_app.repository.UserRepository;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class UserController {

    @Autowired
    private UserRepository userRepository;

  
    @GetMapping("/users/total")
    public long getTotalUsers() {
        return userRepository.count();
    }

   
    @GetMapping("/users/roles")
    public Map<Role, Long> getUsersCountByRole() {
        List<User> users = userRepository.findAll();

        
        Map<Role, Long> roleCountMap = users.stream()
                .collect(Collectors.groupingBy(User::getRole, Collectors.counting()));

        
        Map<Role, Long> completeRoleMap = new EnumMap<>(Role.class);
        for (Role role : Role.values()) {
            completeRoleMap.put(role, roleCountMap.getOrDefault(role, 0L));
        }

        return completeRoleMap;
    }
    
   
}
