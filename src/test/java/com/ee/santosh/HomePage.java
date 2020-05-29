package com.ee.santosh;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class HomePage {
    WebDriver driver;
    String headingSelector = ".jumbotron";
    String checkinDateSelector = "#checkin";
    String checkoutDateSelector = "#checkout";
    String firstNameSelector = "#firstname";
    String surnameSelector = "#lastname";
    String totalpriceSelector = "#totalprice";
    String depositpaidSelector = "#depositpaid";
    String saveButtonSelector = "input[value=' Save ']";
    String deleteButtonSelector = "input[value=Delete]";
    Utility util = new Utility();
    WebDriverWait wait;

    HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 120000);
    }

    public HomePage deleteOrder() {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(this.deleteButtonSelector)));
            element.click();
        } catch (Exception e) {
            System.out.println("Order not found. Not to worry.");
        }
        return this;
    }

    public HomePage deleteAllOrders() {
        List<WebElement> el = driver.findElements(By.cssSelector(this.deleteButtonSelector));
        for ( WebElement e : el ) {
            e.click();
        }
        return this;
    }

    public HomePage verifyOrderCountToBe(int expectedCount) throws Exception {
        int i = 0;
        int totalAttempts = 10;
        int actualCount = 0;
        do {
            try {
                List<WebElement> el = driver.findElements(By.cssSelector(this.deleteButtonSelector));
                actualCount = el.size();
                if(actualCount == expectedCount) {
                    return this;
                }
            } catch (Exception e) {
                System.out.println("retrying method ...");
            }
            Thread.sleep(500);
        } while(++i < totalAttempts);
        Assert.assertEquals(actualCount, expectedCount);
        if( i == totalAttempts) {
            throw new Exception("verifyOrderCount failed...!");
        }
        return this;
    }

    private boolean isOrderCreatedSuccessfully() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(this.deleteButtonSelector)));
        } catch (Exception e) {
            System.out.println("Unable to save the order. Please check the log");
            return false;
        }
        return true;
    }

    private void waitUntilSave() {
        wait.until((WebDriver driver) -> {
            WebElement saveButton = driver.findElement(By.cssSelector(this.saveButtonSelector));
            saveButton.click();
            String firstname = util.runJS(driver, "return document.getElementById('firstname').value;");
            return firstname.equals("");
        });
    }

    public HomePage open(String url) {
        driver.get(url);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(this.headingSelector)));
        return this;
    }

    public HomePage setCheckinDate(String checkinDate) throws InterruptedException {
        WebElement dateBox = driver.findElement(By.cssSelector(this.checkinDateSelector));
        dateBox.sendKeys(checkinDate);
        util.waitForJS(driver,"return document.getElementById('checkin').value;", checkinDate);
        dateBox.sendKeys(Keys.ENTER);
        return this;
    }

    public HomePage setCheckoutDate(String checkoutDate) throws InterruptedException {
        WebElement dateBox = driver.findElement(By.cssSelector(this.checkoutDateSelector));
        dateBox.sendKeys(checkoutDate);
        util.waitForJS(driver, "return document.getElementById('checkout').value;", checkoutDate);
        dateBox.sendKeys(Keys.ENTER);
        return this;
    }

    public HomePage setFirstName(String firstName) throws InterruptedException {
        WebElement element = driver.findElement(By.cssSelector(this.firstNameSelector));
        element.sendKeys(firstName);
        util.waitForJS(driver, "return document.getElementById('firstname').value;", firstName);
        return this;
    }

    public HomePage setLastName(String lastName) throws InterruptedException {
        WebElement element = driver.findElement(By.cssSelector(this.surnameSelector));
        element.sendKeys(lastName);
        util.waitForJS(driver,"return document.getElementById('lastname').value;", lastName);
        return this;
    }

    public HomePage setTotalPrice(String  totalPrice) throws InterruptedException {
        WebElement element = driver.findElement(By.cssSelector(this.totalpriceSelector));
        element.sendKeys(totalPrice);
        util.waitForJS(driver,"return document.getElementById('totalprice').value;", totalPrice);
        return this;
    }

    public HomePage setDeposit(String isPaid) {
        Select depositSelector = new Select(driver.findElement(By.cssSelector(this.depositpaidSelector)));
        depositSelector.selectByVisibleText(isPaid);

        wait.until((WebDriver dr1) -> {
            String selectedOption = depositSelector.getFirstSelectedOption().getText();
            return selectedOption.equals(isPaid);
        });
        return this;
    }

    public HomePage saveBooking() {
        this.waitUntilSave();
        boolean isOrderCreated = this.isOrderCreatedSuccessfully();
        Assert.assertEquals(isOrderCreated, true);
        return this;
    }
}
