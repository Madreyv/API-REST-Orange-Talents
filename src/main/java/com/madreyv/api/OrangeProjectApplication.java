package com.madreyv.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class OrangeProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrangeProjectApplication.class, args);
	}

}
