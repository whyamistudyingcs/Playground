package com.example.playground.bdd;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition {
    @Given("test cucumber basic setup.")
    public void setUpTest() {
        System.out.println("Cucumber setup is successful!");
    }

    @Given("user is on the login page")
    public void userIsOnLoginPage() {
        System.out.println("User is on the login page.");
    }

    @When("user enters valid credentials")
    public void userEntersValidCredentials() {
        System.out.println("User enters valid credentials.");
    }

    @When("user enters invalid credentials")
    public void userEntersInvalidCredentials() {
        System.out.println("User enters invalid credentials.");
    }

    @When("user clicks on the login button")
    public void userClicksOnLoginButton() {
        System.out.println("User clicks on the login button.");
    }

    @Then("user should be redirected to the dashboard")
    public void userShouldBeRedirectedToDashboard() {
        System.out.println("User is redirected to the dashboard.");
    }

    @Then("user should see an error message")
    public void userShouldSeeErrorMessage() {
        System.out.println("User sees an error message.");
    }
}
