package com.example.playground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(GatewayProperties.class)
public class PlaygroundApplication {
	public static void main(String[] args) {
		SpringApplication.run(PlaygroundApplication.class, args);
	}
}
