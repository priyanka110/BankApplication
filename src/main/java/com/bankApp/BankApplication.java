package com.bankApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages={"com.bankApp"}, considerNestedRepositories=true)
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}
}
