package com.ee.santosh;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    WebDriver driver;
    String headingSelector = ".jumbotron";
    String checkinDateSelector = "#checkin";
    String checkoutDateSelector = "#checkout";

    HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public HomePage open(String url) {
        //Navigate to a URL
        driver.get(url);
        // Wait until the page shows up
        WebDriverWait wait = new WebDriverWait(this.driver, 10);
        // Explicitly wait for the heading to be present on the page
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(this.headingSelector)));
        return this;
    }

    public HomePage setCheckinDate(String checkinDate) {
        WebElement dateBox = driver.findElement(By.cssSelector(this.checkinDateSelector));
        dateBox.sendKeys(checkinDate);
        return this;
    }

    public HomePage setCheckoutDate(String checkoutDate) {
        WebElement dateBox = driver.findElement(By.cssSelector(this.checkoutDateSelector));
        dateBox.sendKeys(checkoutDate);
        return this;
    }
}
