package com.travel.travelagent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.travel.travelagent.entity.Role;
import com.travel.travelagent.entity.Trip;
import com.travel.travelagent.entity.User;
import com.travel.travelagent.repository.RoleRepository;
import com.travel.travelagent.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public User saveUser(User user) {

	    user.setPassword(passwordEncoder.encode(user.getPassword()));

	    Role role = roleRepository.findByRoleName("USER")
	            .orElseThrow(() -> new RuntimeException("Role not found"));

	    user.setRoles(List.of(role));

	    if (user.getTrips() != null) {
	        user.getTrips().forEach(trip -> trip.setUser(user));
	    }

	    return userRepository.save(user);
	}
}
