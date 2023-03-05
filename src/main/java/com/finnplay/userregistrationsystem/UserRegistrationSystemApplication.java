package com.finnplay.userregistrationsystem;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserRegistrationSystemApplication {
	//todo move docker-compose to environment variables
	//todo add use ssl to the database

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(UserRegistrationSystemApplication.class, args);
	}

}
