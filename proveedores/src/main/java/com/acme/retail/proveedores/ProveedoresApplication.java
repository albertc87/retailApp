package com.acme.retail.proveedores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.acme.retail.repository")
public class ProveedoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProveedoresApplication.class, args);
	}
}
