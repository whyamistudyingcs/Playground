package com.example.playground.Interfaces;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "security_auth")
public class HealthController {
    @GetMapping("/health")
    public String checkHealth() {
        return "Application is up and running!";
    }
}
