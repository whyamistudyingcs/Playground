package com.example.playground.Domain.Config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "scheduling")
@Data
public class SchedulingConfig {
    private Map<String, Map<String, JobConfig>> jobs;
    @Getter
    @Setter
    public static class JobConfig {
        private Boolean enabled;
        private String cronExpression;
    }
}
