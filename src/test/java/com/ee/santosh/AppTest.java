package com.ee.santosh;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pojo.classes.Data;
import com.pojo.classes.DataItem;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
   Description
   -----------
   This is the main Class which runs all the tests
   There is only one page, HomePage
   Page Object pattern is used to abstract all the actions and verifications required for the Home Page
   Methods which are not specific to Home Page are pulled into a Utility class.
   Data is stored in a json file, which makes it easy to maintain test data.
*/

public class AppTest {
    private final String url = "http://hotel-test.equalexperts.io/";
    private final String jsonDataFile = "src/test/resources/data/data.json";
    private WebDriver driver;
    private HomePage homePage;
    private Data data;
    @BeforeClass
    public void beforeClass() throws IOException, InterruptedException {

        data = new Data();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String fileData = new String(Files.readAllBytes(Paths.get(jsonDataFile)));
        data = gson.fromJson(fileData, Data.class);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        homePage = new HomePage(driver);
        homePage
                .open(url)
                .deleteAllOrders();
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    @BeforeTest
    public void beforeTest() {
        // This is a placeholder.
        // Code which needs to be run before a test goes here
    }
    @AfterTest
    public void afterTest() {
        // This is a placeholder.
        // Code which needs to be run after a test goes here
    }

    // This test checks if the user is able to book a hotel with deposit (Deposit = true), and delete the booking.
    @Test(enabled=true)
    public void Test_1_book_a_hotel_with_deposit() throws Exception {
        DataItem dataItem = data.getTest_1().getData().get(0);
        homePage
                .setCheckoutDate(dataItem.getCheckoutDate())
                .setFirstName(dataItem.getFirstname())
                .setLastName(dataItem.getLastname())
                .setTotalPrice(dataItem.getTotalPrice())
                .setDeposit(dataItem.getDeposit())
                .setCheckinDate(dataItem.getCheckinDate())
                .saveBooking()
                .verifyOrderCountToBe(1)
                .deleteOrder()
                .verifyOrderCountToBe(0);
    }
    // This test checks if the user is able to book a hotel without a deposit (Deposit = false), and delete the booking.
    @Test(enabled=true)
    public void Test_2_book_a_hotel_without_deposit() throws Exception {
        DataItem dataItem = data.getTest_2().getData().get(0);
        homePage
                .setCheckoutDate(dataItem.getCheckoutDate())
                .setFirstName(dataItem.getFirstname())
                .setLastName(dataItem.getLastname())
                .setTotalPrice(dataItem.getTotalPrice())
                .setDeposit(dataItem.getDeposit())
                .setCheckinDate(dataItem.getCheckinDate())
                .saveBooking()
                .verifyOrderCountToBe(1)
                .deleteOrder()
                .verifyOrderCountToBe(0);
    }
    // This test checks if the user is able to book two hotels, and delete both bookings.
    @Test(enabled=true)
    public void Test_3_book_two_hotels() throws Exception {
        DataItem dataItem;
        dataItem = data.getTest_3().getData().get(0);
        homePage
                .setCheckoutDate(dataItem.getCheckoutDate())
                .setFirstName(dataItem.getFirstname())
                .setLastName(dataItem.getLastname())
                .setTotalPrice(dataItem.getTotalPrice())
                .setDeposit(dataItem.getDeposit())
                .setCheckinDate(dataItem.getCheckinDate())
                .saveBooking()
                .verifyOrderCountToBe(1);

        dataItem = data.getTest_3().getData().get(1);
        homePage
                .setCheckoutDate(dataItem.getCheckoutDate())
                .setFirstName(dataItem.getFirstname())
                .setLastName(dataItem.getLastname())
                .setTotalPrice(dataItem.getTotalPrice())
                .setDeposit(dataItem.getDeposit())
                .setCheckinDate(dataItem.getCheckinDate())
                .saveBooking()
                .verifyOrderCountToBe(2)
                .deleteOrder()
                .verifyOrderCountToBe(1)
                .deleteOrder()
                .verifyOrderCountToBe(0);
    }
}
