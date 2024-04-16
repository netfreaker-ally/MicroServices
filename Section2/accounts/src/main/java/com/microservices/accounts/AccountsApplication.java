package com.microservices.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareimpl")
@OpenAPIDefinition(
		info=@Info(
				title="Accounts Microservices REST API Documentaion",
				description = "Eazybank Accounts microservices REST API Documentation",
				version = "v1",
				contact=@Contact(
						name="Hanuma Ramavath",
						email="hanumaramavath@techawave.net",
						url="http://www.google.com"
						
						),
				license=@License(
						name="Apache 2.0",
						url="https://www.google.com"
						)
				
				
				),
		externalDocs=@ExternalDocumentation(
				description = "Eazybank Accounts microservices REST API Documentation",
				url="https://www.google.com"

				)
		
		
		)

public class AccountsApplication {

	public static void main(String[] args) {

		SpringApplication.run(AccountsApplication.class, args);
	}

}
