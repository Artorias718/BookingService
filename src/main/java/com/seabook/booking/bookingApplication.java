package com.seabook.booking;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDiscoveryClient
@SpringBootApplication
public class bookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(bookingApplication.class, args);
	}

}
