package com.ee.santosh;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage {
    WebDriver driver;
    String headingSelector = ".jumbotron";
    String checkinDateSelector = "#checkin";
    String checkoutDateSelector = "#checkout";
    String firstNameSelector = "#firstname";
    String surnameSelector = "#lastname";
    String totalpriceSelector = "#totalprice";
    String depositpaidSelector = "#depositpaid";
    String saveButtonSelector = "input[type=button]";
    String deleteButtonSelector = "input[value=Delete]";
    Utility util = new Utility();

    HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage deleteOrder() {
        WebDriverWait wait = new WebDriverWait(this.driver, 2);
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(this.deleteButtonSelector)));
            element.click();
        } catch (Exception e) {
            System.out.println("Order not found. Not to worry.");
        }
        return this;
    }

    private boolean isOrderCreatedSuccessfully() {
        WebDriverWait wait = new WebDriverWait(this.driver, 15);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(this.deleteButtonSelector)));
        } catch (Exception e) {
            System.out.println("Unable to save the order. Please check the log");
            return false;
        }
        return true;
    }

    public HomePage open(String url) {
        driver.get(url);
        WebDriverWait wait = new WebDriverWait(this.driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(this.headingSelector)));
        return this;
    }

    public HomePage setCheckinDate(String checkinDate) {
        WebElement dateBox = driver.findElement(By.cssSelector(this.checkinDateSelector));
        dateBox.sendKeys(checkinDate);
        // now get the checkInDate and wait until it is set to expected value
        util.waitForJS(driver,"return document.getElementById('checkin').value;", checkinDate);
        dateBox.sendKeys(Keys.ENTER);
        return this;
    }

    public HomePage setCheckoutDate(String checkoutDate) {
        WebElement dateBox = driver.findElement(By.cssSelector(this.checkoutDateSelector));
        dateBox.sendKeys(checkoutDate);
        util.waitForJS(driver, "return document.getElementById('checkout').value;", checkoutDate);
        return this;
    }

    public HomePage setFirstName(String firstName) {
        WebElement element = driver.findElement(By.cssSelector(this.firstNameSelector));
        element.sendKeys(firstName);
        return this;
    }

    public HomePage setLastName(String lastName) {
        WebElement element = driver.findElement(By.cssSelector(this.surnameSelector));
        element.sendKeys(lastName);
        return this;
    }

    public HomePage setTotalPrice(String  totalPrice) {
        WebElement element = driver.findElement(By.cssSelector(this.totalpriceSelector));
        element.sendKeys(totalPrice);
        return this;
    }

    public HomePage setDeposit(String isPaid) {
        Select depositSelector = new Select(driver.findElement(By.cssSelector(this.depositpaidSelector)));
        depositSelector.selectByVisibleText(isPaid);
        return this;
    }

    public void saveBooking() {
        WebElement element = driver.findElement(By.cssSelector(this.saveButtonSelector));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
        boolean isOrderCreated = this.isOrderCreatedSuccessfully();
        Assert.assertEquals(isOrderCreated, true);
    }
}
