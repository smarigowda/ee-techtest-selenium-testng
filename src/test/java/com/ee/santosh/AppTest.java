package com.ee.santosh;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class AppTest {
    final String url = "http://hotel-test.equalexperts.io/";
    WebDriver driver;
    HomePage homePage;
    @BeforeClass
    public void beforeAnyTestRun() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        homePage = new HomePage(driver);
        homePage
                .open(url)
                .deleteAllOrders();
    }
    @AfterClass
    public void afterAllTestsRun() {
        driver.quit();
    }
    @BeforeTest
    public void beforeTest() {

    }
    @AfterTest
    public void afterTest() {

    }
    @Test(enabled=true)
    public void Test_1_book_a_hotel_with_deposit() throws Exception {
        homePage
                .setCheckoutDate("2020-05-28")
                .setFirstName("Santosh")
                .setLastName("Marigowda")
                .setTotalPrice("100")
                .setDeposit("true")
                .setCheckinDate("2020-05-20")
                .saveBooking()
                .verifyOrderCountToBe(1)
                .deleteOrder()
                .verifyOrderCountToBe(0);
    }
    @Test(enabled=true)
    public void Test_2_book_a_hotel_without_deposit() throws Exception {
        homePage
                .setCheckoutDate("2020-05-28")
                .setFirstName("Santosh")
                .setLastName("Marigowda")
                .setTotalPrice("100")
                .setDeposit("false")
                .setCheckinDate("2020-05-20")
                .saveBooking()
                .verifyOrderCountToBe(1)
                .deleteOrder()
                .verifyOrderCountToBe(0);
    }
    @Test(enabled=true)
    public void Test_3_book_two_hotels() throws Exception {
        homePage
                .setCheckoutDate("2020-05-28")
                .setFirstName("Santosh")
                .setLastName("Marigowda")
                .setTotalPrice("100")
                .setDeposit("false")
                .setCheckinDate("2020-05-20")
                .saveBooking()
                .verifyOrderCountToBe(1);
        homePage
                .setCheckoutDate("2020-05-28")
                .setFirstName("Dattatreya")
                .setLastName("Sakrepatna")
                .setTotalPrice("200")
                .setDeposit("true")
                .setCheckinDate("2020-04-20")
                .saveBooking()
                .verifyOrderCountToBe(2)
                .deleteOrder()
                .deleteOrder()
                .verifyOrderCountToBe(0);
    }
}
