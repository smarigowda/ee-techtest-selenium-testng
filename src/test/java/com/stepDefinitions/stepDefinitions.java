package com.stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class stepDefinitions {
    @Given("^User opens the application$")
    public void user_opens_the_application() {
        System.out.println("User Opens the Application");
    }

    @When("^User provides all the details with deposit true$")
    public void user_provides_all_the_details_with_deposit_true() {
        System.out.println("^User provides all the details with deposit true$");
    }

    @When("^User Saves the booking$")
    public void user_saves_the_booking() {
        System.out.println("^User Saves the booking$");
    }

    @Then("^Booking should be saved successfully$")
    public void booking_should_be_saved_successfully() {
        System.out.println("^Booking should be saved successfully$");
    }
}
