package com.foodcartservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class FoodcartserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodcartserviceApplication.class, args);
	}


}
