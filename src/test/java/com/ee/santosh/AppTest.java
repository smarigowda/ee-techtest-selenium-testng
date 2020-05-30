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

public class AppTest {
    private final String url = "http://hotel-test.equalexperts.io/";
    private final String jsonDataFile = "src/test/java/com/ee/santosh/data.json";
    private WebDriver driver;
    private HomePage homePage;
    private Data data;
    @BeforeClass
    public void beforeClass() throws IOException {

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
