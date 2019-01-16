package com.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigServer
public class MssConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(MssConfigApplication.class, args);
	}

}

