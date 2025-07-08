package com.example.playground.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class SampleUnitTest {
    @Test
    public void junitShouldWorkWithCucumberTest() {
        System.out.println("JUnit test is running successfully!");    
    }
    @Test
    public void jsonAssertShouldWork() throws IOException, JSONException {
        String inflowJson = new String(Files.readAllBytes(Paths.get("src/test/resources/json/fundInFlow.json")));
        JsonArray inflowFunds = JsonParser.parseString(inflowJson).getAsJsonObject().getAsJsonArray("funds");
        String outflowJson = new String(Files.readAllBytes(Paths.get("src/test/resources/json/fundOutFlow.json")));
        JsonArray outflowFunds = JsonParser.parseString(outflowJson).getAsJsonObject().getAsJsonArray("contents");
        assertEquals(inflowFunds.size(), outflowFunds.size());
        JSONAssert.assertEquals(inflowFunds.toString(), outflowFunds.toString(), JSONCompareMode.LENIENT);
    }
}
