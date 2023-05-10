package com.pragma.powerup.usermicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserMicroserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserMicroserviceApplication.class, args);
	}

}
