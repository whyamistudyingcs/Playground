package com.example.playground;
import lombok.Data;
import lombok.RequiredArgsConstructor;
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
