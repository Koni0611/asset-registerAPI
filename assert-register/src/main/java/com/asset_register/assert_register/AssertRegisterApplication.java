package com.asset_register.assert_register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.asset_register.assert_register")
@EnableJpaRepositories(basePackages = "com.asset_register.assert_register.repositories")
public class AssertRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssertRegisterApplication.class, args);
	}
}