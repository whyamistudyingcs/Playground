package com.example.playground.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",  //Path to your feature files
    glue ="com.example.playground.bdd" , // Package containg step definitions
    plugin = {"pretty","html:target/cucumber-reports.html"}, //Report Generation
    monochrome = true // Makes Output Readable
)
public class RunCucumberTest {
}
