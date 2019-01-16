package com.cloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {

	@Value("${server}")
	private String profile;
	
	@GetMapping("/profile")
	public String getProfile(){
		return profile;
	};
}
