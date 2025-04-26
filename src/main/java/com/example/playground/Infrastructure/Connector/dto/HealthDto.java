package com.example.playground.Infrastructure.Connector.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class HealthDto {
    private String status;
    private String timestamp;
    private String uptime;
    private String environment;
}
