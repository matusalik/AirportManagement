package com.management.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.management.Repositories.UserRepository;
import com.management.Users.AppUser;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserRepository userRepository;
	
	
	@GetMapping("/findAllUsers")
	public @ResponseBody Iterable<AppUser>getUsers(){
		return userRepository.findAll();
	}
}
