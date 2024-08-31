package com.ef.interview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class InterviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterviewApplication.class, args);
	}
	
	@Bean
	//@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
