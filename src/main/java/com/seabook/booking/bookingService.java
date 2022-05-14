package com.seabook.booking;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDiscoveryClient
@SpringBootApplication
public class bookingService {

	public static void main(String[] args) {
		SpringApplication.run(bookingService.class, args);
	}

	static final String topicExchangeName = "spring-boot-exchange";



}
