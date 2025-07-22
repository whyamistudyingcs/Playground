package com.example.playground.Infrastructure.Connector.dto;

import lombok.Data;

@Data
public class HealthDto {
    private String status;
    private String timestamp;
    private String uptime;
    private String environment;
}
