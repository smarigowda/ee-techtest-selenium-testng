package com.ee.santosh;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;



public class AppTest {
    final String url = "http://hotel-test.equalexperts.io/";
    @Test
    public void Test_1_book_with_deposit_true() throws Exception {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        HomePage homePage = new HomePage(driver);

        homePage
                .open(url)
                .deleteOrder() // cleanup task, may be this has to go into before block
                .setCheckoutDate("2020-05-28")
                .setFirstName("Santosh")
                .setLastName("Marigowda")
                .setTotalPrice("100")
                .setDeposit("false")
                .setCheckinDate("2020-05-20")
                .saveBooking();

        // Just to see the final result visually, will be removed in real test
        Thread.sleep(3000);
        driver.quit();
    }
}
