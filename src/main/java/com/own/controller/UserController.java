package com.own.controller;

import com.own.dto.UserRegDto;
import com.own.service.UserService;
import com.own.utils.AppStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserController {
	
	@Autowired
	UserService userService;
	@GetMapping(value = "/")
	public String user() {
		return "<h1>Hello user</h1>";
	}
	@GetMapping(value = "/mine")
	public String userGuide() {
		return "<h1>Hello user you can do only permitted operations </h1>";
	}
	@PostMapping(value = "register")
	public ResponseEntity<String> registerUser(@Valid @RequestBody @NotNull UserRegDto userReq){
		log.info("registering user is started");
		userService.doRegister(userReq);

		return null;
		
	}
	@GetMapping(value = "/getAllUsers")
	public @ResponseBody ResponseEntity<String> getAllUsers(){
		String userJsonData= userService.getAllUsers();
		return AppStringUtils.isEmpty(userJsonData)? 
				ResponseEntity.ok().body("User Not Founds"):
			ResponseEntity.ok().body(userJsonData);
	}
}
