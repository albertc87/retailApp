package com.acme.retail.cartera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.acme.retail.repository")
public class CarteraApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarteraApplication.class, args);
	}
}
