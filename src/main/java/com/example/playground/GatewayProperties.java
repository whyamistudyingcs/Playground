package com.example.playground;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


import java.util.Map;

@Component
@ConfigurationProperties("gateway")
@Data
@RequiredArgsConstructor
public class GatewayProperties {
    private Map<String, Map<String, String>> settlement;
}
