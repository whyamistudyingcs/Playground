package com.example.playground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableConfigurationProperties(GatewayProperties.class)
public class PlaygroundApplication {
	public static void main(String[] args) {
		SpringApplication.run(PlaygroundApplication.class, args);
	}
}
