package com.rong.friend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rong.friend.model.User;
import com.rong.friend.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/getuser")
	public String getUser(@RequestParam String id) {
		String namenumber="";
		try {
			User user=userService.findUserById(id);
			namenumber=user.getNamenumber();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return namenumber;
	}
}
