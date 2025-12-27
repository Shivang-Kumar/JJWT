package com.auth.JWT.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Endpoints {
	
	
	@RequestMapping("/api/secured")
	public ResponseEntity<String> securedFuction()
	{
		return ResponseEntity.ok().body("You have successfully accessed the secured endpoint!WOW (:");
	}
	
	
	@RequestMapping("/api/non-secured")
	public ResponseEntity<String> nonSecuredFuction()
	{
		return ResponseEntity.ok().body("You have successfully accessed the Non secured  endpoint!Not so WOW (:");
	}

}
