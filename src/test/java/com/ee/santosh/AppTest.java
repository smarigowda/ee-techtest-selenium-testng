package com.ee.santosh;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AppTest {
    @Test
    public void Test1() throws InterruptedException {
        //setup the chromedriver using WebDriverManager
        WebDriverManager.chromedriver().setup();

        //Create driver object for Chrome
        WebDriver driver = new ChromeDriver();
        // Setup an implicit wait of 10 sec
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        HomePage homePage = new HomePage(driver);
        homePage
                .open("http://hotel-test.equalexperts.io/")
                .setCheckinDate("2020-05-20")
                .setCheckoutDate("2020-05-28")
                .setFirstName("Santosh")
                .setLastName("Marigowda")
                .setTotalPrice("100");

        Select depositSelector = new Select(driver.findElement(By.cssSelector("#depositpaid")));
        depositSelector.selectByVisibleText("true");
        Thread.sleep(5000);

        //quit the browser
        driver.quit();
    }
}
