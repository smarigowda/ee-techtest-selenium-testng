package com.stepDefinitions;

import com.ee.santosh.HomePage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pojo.classes.Data;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class stepDefinitions {

    private final String url = "http://hotel-test.equalexperts.io/";
    private final String jsonDataFile = "src/test/java/com/ee/santosh/data.json";
    private WebDriver driver;
    private HomePage homePage;
    private Data data;

    @Given("^User opens the application$")
    public void user_opens_the_application() throws IOException {

        System.out.println("User Opens the Application");
        data = new Data();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String fileData = new String(Files.readAllBytes(Paths.get(jsonDataFile)));
        data = gson.fromJson(fileData, Data.class);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        homePage = new HomePage(driver);
        homePage.open(url);
        driver.close();
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
