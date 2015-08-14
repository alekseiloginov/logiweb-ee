package com.tsystems.javaschool.loginov.logiweb;

import com.google.common.base.Predicate;
import com.sun.istack.Nullable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class SeleniumMainTest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver","/Users/alex/Desktop/chromedriver");
        driver = new ChromeDriver();
        baseUrl = "http://localhost:8080";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testSeleniumMain() throws Exception {
        driver.get(baseUrl + "/logiweb-ee/");
        driver.findElement(By.id("myBtn")).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("aloginov");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("1234");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.linkText("Trucks")).click();
        driver.findElement(By.className("jtable-toolbar-item-add-record")).click();
        driver.findElement(By.id("Edit-plate_number")).clear();
        driver.findElement(By.id("Edit-plate_number")).sendKeys("ZH12345");
        driver.findElement(By.id("Edit-driver_number")).clear();
        driver.findElement(By.id("Edit-driver_number")).sendKeys("2");
        driver.findElement(By.id("Edit-capacity")).clear();
        driver.findElement(By.id("Edit-capacity")).sendKeys("3");
        new Select(driver.findElement(By.id("Edit-location"))).selectByVisibleText("Murmansk");
        driver.findElement(By.id("AddRecordDialogSaveButton")).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='TruckTableContainer']/div/table/tbody/tr[5]/td[6]/button")));
        driver.findElement(By.xpath("//div[@id='TruckTableContainer']/div/table/tbody/tr[5]/td[6]/button")).click();
        new WebDriverWait(driver, 10)
                .ignoring(StaleElementReferenceException.class)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(@Nullable WebDriver driver) {
                        driver.findElement(By.id("Edit-driver_number")).click();
                        return true;
                    }
                });
        driver.findElement(By.id("Edit-driver_number")).sendKeys("3");
        driver.findElement(By.id("EditDialogSaveButton")).click();
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='TruckTableContainer']/div/table/tbody/tr[5]/td[7]/button")));
        driver.findElement(By.xpath("//div[@id='TruckTableContainer']/div/table/tbody/tr[5]/td[7]/button")).click();
        driver.findElement(By.id("DeleteDialogButton")).click();
        driver.findElement(By.linkText("Drivers")).click();
        new WebDriverWait(driver, 10)
                .ignoring(StaleElementReferenceException.class)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(@Nullable WebDriver driver) {
                        driver.findElement(By.className("jtable-toolbar-item-add-record")).click();
                        return true;
                    }
                });
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("Edit-name")));
        driver.findElement(By.id("Edit-name")).clear();
        driver.findElement(By.id("Edit-name")).sendKeys("Vasya");
        driver.findElement(By.id("Edit-surname")).click();
        driver.findElement(By.id("Edit-surname")).clear();
        driver.findElement(By.id("Edit-surname")).sendKeys("Pupkin");
        driver.findElement(By.id("Edit-email")).clear();
        driver.findElement(By.id("Edit-email")).sendKeys("vas@abc.com");
        driver.findElement(By.id("Edit-password")).clear();
        driver.findElement(By.id("Edit-password")).sendKeys("1234");
        driver.findElement(By.id("Edit-worked_hours")).clear();
        driver.findElement(By.id("Edit-worked_hours")).sendKeys("0");
        driver.findElement(By.id("AddRecordDialogSaveButton")).click();
        driver.findElement(By.linkText("Drivers")).click();
        new WebDriverWait(driver, 10)
                .ignoring(StaleElementReferenceException.class)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(@Nullable WebDriver driver) {
                        driver.findElement(By.xpath("//div[@id='DriverTableContainer']/div/table/tbody/tr[8]/td[9]/button")).click();
                        return true;
                    }
                });
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='jtable-edit-form']/div[6]/div[2]/div[2]/span")));
        driver.findElement(By.xpath("//form[@id='jtable-edit-form']/div[6]/div[2]/div[2]/span")).click();
        driver.findElement(By.id("EditDialogSaveButton")).click();
        driver.findElement(By.cssSelector("tr.jtable-data-row.jtable-row-updated > td.jtable-command-column > button.jtable-command-button.jtable-delete-command-button")).click();
        driver.findElement(By.id("DeleteDialogButton")).click();
        driver.findElement(By.linkText("Freights")).click();
        new WebDriverWait(driver, 10)
                .ignoring(StaleElementReferenceException.class)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(@Nullable WebDriver driver) {
                        driver.findElement(By.linkText("Orders")).click();
                        return true;
                    }
                });
        driver.findElement(By.id("map")).click();
        new WebDriverWait(driver, 15);
        new WebDriverWait(driver, 20)
                .ignoring(StaleElementReferenceException.class)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(@Nullable WebDriver driver) {
                        driver.findElement(By.cssSelector("button")).click();
                        return true;
                    }
                });
        driver.findElement(By.linkText("aloginov")).click();
        driver.findElement(By.linkText("Log Out")).click();
        driver.findElement(By.id("myBtn")).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("gchichvarkin");
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("1234");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.linkText("Orders")).click();
        driver.get(baseUrl + "/logiweb-ee/drivers");
        driver.findElement(By.id("myBtn")).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Password?")));
        driver.findElement(By.linkText("Password?")).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("plus79045568217@gmail.com");
        driver.findElement(By.xpath("(//button[@type='submit'])[4]")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
