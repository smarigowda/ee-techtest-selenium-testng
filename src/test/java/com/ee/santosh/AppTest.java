package com.ee.santosh;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
;
        // Setup an implicit wait of 10 sec
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Navigate to a URL
        driver.get("http://hotel-test.equalexperts.io/");

        // Wait until the page shows up
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // Explicitly wait for the heading to be present on the page
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".jumbotron")));

        Thread.sleep(2000);
        //quit the browser
        driver.quit();
    }
}
