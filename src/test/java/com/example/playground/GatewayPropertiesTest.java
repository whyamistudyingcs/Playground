package com.example.playground;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GatewayPropertiesTest {

    @Autowired
    private GatewayProperties gatewayProperties;

    @Test
    public void testPropertiesBinding() {
        Map<String, Map<String, String>> clientIdsMap = gatewayProperties.getClientIdsMap();

        // Assert the nested map structure
        assertThat(clientIdsMap)
                .containsOnlyKeys("FINIQ_SCB", "FINIQ_WLB"); // Verify top-level keys

        assertThat(clientIdsMap.get("FINIQ_SCB"))
                .containsEntry("HK", "1234")
                .containsEntry("UK", "5678");

        assertThat(clientIdsMap.get("FINIQ_WLB"))
                .containsEntry("CHN", "31415");
    }
}