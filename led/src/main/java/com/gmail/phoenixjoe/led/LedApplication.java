package com.gmail.phoenixjoe.led;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LedApplication {

	public static void main(String[] args) {
		SpringApplication.run(LedApplication.class, args);
	}

}
