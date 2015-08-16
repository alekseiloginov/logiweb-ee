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
        System.setProperty("webdriver.chrome.driver","/Users/alex/Desktop/IdeaProjects/logiweb-ee/chromedriver");
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

        // CRUD Trucks
        driver.findElement(By.linkText("Trucks")).click();
        driver.findElement(By.className("jtable-toolbar-item-add-record")).click();
        driver.findElement(By.id("Edit-plateNumber")).clear();
        driver.findElement(By.id("Edit-plateNumber")).sendKeys("ZH12345");
        driver.findElement(By.id("Edit-driverNumber")).clear();
        driver.findElement(By.id("Edit-driverNumber")).sendKeys("2");
        driver.findElement(By.id("Edit-capacity")).clear();
        driver.findElement(By.id("Edit-capacity")).sendKeys("3");
        new Select(driver.findElement(By.id("Edit-location"))).selectByVisibleText("Murmansk");
        driver.findElement(By.id("AddRecordDialogSaveButton")).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='TruckTableContainer']/div/table/tbody/tr[5]/td[6]/button")));
        driver.findElement(By.xpath("//div[@id='TruckTableContainer']/div/table/tbody/tr[5]/td[6]/button")).click();
        new WebDriverWait(driver, 10)
                .ignoring(WebDriverException.class)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(@Nullable WebDriver driver) {
                        driver.findElement(By.id("Edit-driverNumber")).click();
                        return true;
                    }
                });
        driver.findElement(By.id("Edit-driverNumber")).clear();
        driver.findElement(By.id("Edit-driverNumber")).sendKeys("3");
        driver.findElement(By.id("EditDialogSaveButton")).click();
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='TruckTableContainer']/div/table/tbody/tr[5]/td[7]/button")));
        driver.findElement(By.xpath("//div[@id='TruckTableContainer']/div/table/tbody/tr[5]/td[7]/button")).click();
        driver.findElement(By.id("DeleteDialogButton")).click();

        // CRUD Drivers
        driver.findElement(By.linkText("Drivers")).click();
        new WebDriverWait(driver, 10)
                .ignoring(WebDriverException.class)
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
        driver.findElement(By.id("Edit-workedHours")).clear();
        driver.findElement(By.id("Edit-workedHours")).sendKeys("0");
        driver.findElement(By.id("AddRecordDialogSaveButton")).click();
        driver.findElement(By.linkText("Drivers")).click();
        new WebDriverWait(driver, 10)
                .ignoring(WebDriverException.class)
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

        // ORDER
        new WebDriverWait(driver, 10)
                .ignoring(WebDriverException.class)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(@Nullable WebDriver driver) {
                        driver.findElement(By.linkText("Orders")).click();
                        return true;
                    }
                });

        // add order
        new WebDriverWait(driver, 10)
                .ignoring(WebDriverException.class)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(@Nullable WebDriver driver) {
                        driver.findElement(By.className("jtable-toolbar-item-add-record")).click();
                        return true;
                    }
                });
        driver.findElement(By.id("AddRecordDialogSaveButton")).click();

        // add waypoints
        driver.findElement(By.xpath("(//img[@title='View waypoints'])[4]")).click();
        new WebDriverWait(driver, 10)
                .ignoring(WebDriverException.class)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(@Nullable WebDriver driver) {
                        driver.findElement(By.className("jtable-child-table-container")).findElement(By.className("jtable-toolbar-item-add-record")).click();
                        return true;
                    }
                });
        new Select(driver.findElement(By.id("Edit-location"))).selectByVisibleText("Moscow");
        new Select(driver.findElement(By.id("Edit-freight"))).selectByVisibleText("Laptops");
        driver.findElement(By.xpath("(//button[@id='AddRecordDialogSaveButton'])[2]")).click();

        new WebDriverWait(driver, 10)
                .ignoring(WebDriverException.class)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(@Nullable WebDriver driver) {
                        driver.findElement(By.className("jtable-child-table-container")).findElement(By.className("jtable-toolbar-item-add-record")).click();
                        return true;
                    }
                });
        new WebDriverWait(driver, 10)
                .ignoring(WebDriverException.class)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(@Nullable WebDriver driver) {
                        new Select(driver.findElement(By.id("Edit-location"))).selectByVisibleText("Peterburg");
                        return true;
                    }
                });
        new Select(driver.findElement(By.id("Edit-freight"))).selectByVisibleText("Laptops");
        driver.findElement(By.xpath("(//button[@id='AddRecordDialogSaveButton'])[2]")).click();

        new WebDriverWait(driver, 10)
                .ignoring(WebDriverException.class)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(@Nullable WebDriver driver) {
                        driver.findElement(By.className("jtable-child-table-container")).findElement(By.className("jtable-toolbar-item-add-record")).click();
                        return true;
                    }
                });
        new WebDriverWait(driver, 10)
                .ignoring(WebDriverException.class)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(@Nullable WebDriver driver) {
                        new Select(driver.findElement(By.id("Edit-location"))).selectByVisibleText("Peterburg");
                        return true;
                    }
                });
        new WebDriverWait(driver, 10)
                .ignoring(WebDriverException.class)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(@Nullable WebDriver driver) {
                        new Select(driver.findElement(By.id("Edit-freight"))).selectByVisibleText("Books");
                        return true;
                    }
                });
        driver.findElement(By.xpath("(//button[@id='AddRecordDialogSaveButton'])[2]")).click();

        new WebDriverWait(driver, 10)
                .ignoring(WebDriverException.class)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(@Nullable WebDriver driver) {
                        driver.findElement(By.className("jtable-child-table-container")).findElement(By.className("jtable-toolbar-item-add-record")).click();
                        return true;
                    }
                });
        new WebDriverWait(driver, 10)
                .ignoring(WebDriverException.class)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(@Nullable WebDriver driver) {
                        new Select(driver.findElement(By.id("Edit-location"))).selectByVisibleText("Murmansk");
                        return true;
                    }
                });
        new Select(driver.findElement(By.id("Edit-freight"))).selectByVisibleText("Books");
        driver.findElement(By.xpath("(//button[@id='AddRecordDialogSaveButton'])[2]")).click();

        new WebDriverWait(driver, 10)
                .ignoring(WebDriverException.class)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(@Nullable WebDriver driver) {
                        driver.findElement(By.className("jtable-child-table-container")).findElement(By.className("jtable-toolbar-item-add-record")).click();
                        return true;
                    }
                });
        new WebDriverWait(driver, 10)
                .ignoring(WebDriverException.class)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(@Nullable WebDriver driver) {
                        new Select(driver.findElement(By.id("Edit-location"))).selectByVisibleText("Murmansk");
                        return true;
                    }
                });
        new WebDriverWait(driver, 10)
                .ignoring(WebDriverException.class)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(@Nullable WebDriver driver) {
                        new Select(driver.findElement(By.id("Edit-freight"))).selectByVisibleText("Chairs");
                        return true;
                    }
                });
        driver.findElement(By.xpath("(//button[@id='AddRecordDialogSaveButton'])[2]")).click();

        new WebDriverWait(driver, 10)
                .ignoring(WebDriverException.class)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(@Nullable WebDriver driver) {
                        driver.findElement(By.className("jtable-child-table-container")).findElement(By.className("jtable-toolbar-item-add-record")).click();
                        return true;
                    }
                });
        new WebDriverWait(driver, 15)
                .ignoring(WebDriverException.class)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(@Nullable WebDriver driver) {
                        new Select(driver.findElement(By.id("Edit-location"))).selectByVisibleText("Moscow");
                        return true;
                    }
                });
        new Select(driver.findElement(By.id("Edit-freight"))).selectByVisibleText("Chairs");
        driver.findElement(By.xpath("(//button[@id='AddRecordDialogSaveButton'])[2]")).click();

        // add drivers
        new WebDriverWait(driver, 10)
                .ignoring(WebDriverException.class)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(@Nullable WebDriver driver) {
                        driver.findElement(By.xpath("(//img[@title='View drivers'])[4]")).click();
                        return true;
                    }
                });
        new WebDriverWait(driver, 10)
                .ignoring(WebDriverException.class)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(@Nullable WebDriver driver) {
                        driver.findElement(By.className("jtable-child-table-container")).findElement(By.className("jtable-toolbar-item-add-record")).click();
                        return true;
                    }
                });
        new WebDriverWait(driver, 10)
                .ignoring(WebDriverException.class)
                .until(new Predicate<WebDriver>() {
                    @Override
                    public boolean apply(@Nullable WebDriver driver) {
                        driver.findElement(By.xpath("(//button[@id='AddRecordDialogSaveButton'])[3]")).click();
                        return true;
                    }
                });

//        new WebDriverWait(driver, 10)
//                .ignoring(WebDriverException.class)
//                .until(new Predicate<WebDriver>() {
//                    @Override
//                    public boolean apply(@Nullable WebDriver driver) {
//                        driver.findElement(By.id("map")).click();
//                        return true;
//                    }
//                });
//        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button")));
//        new WebDriverWait(driver, 20)
//                .ignoring(WebDriverException.class)
//                .until(new Predicate<WebDriver>() {
//                    @Override
//                    public boolean apply(@Nullable WebDriver driver) {
//                        driver.findElement(By.cssSelector("button")).click();
//                        return true;
//                    }
//                });
//        driver.findElement(By.linkText("aloginov")).click();
//        driver.findElement(By.linkText("Log Out")).click();
//        driver.findElement(By.id("myBtn")).click();
//        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
//        driver.findElement(By.id("username")).clear();
//        driver.findElement(By.id("username")).sendKeys("gchichvarkin");
//        driver.findElement(By.id("password")).clear();
//        driver.findElement(By.id("password")).sendKeys("1234");
//        driver.findElement(By.xpath("//button[@type='submit']")).click();
//        driver.findElement(By.linkText("Orders")).click();
//        driver.findElement(By.id("map")).click();
//        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button")));
//        new WebDriverWait(driver, 20)
//                .ignoring(WebDriverException.class)
//                .until(new Predicate<WebDriver>() {
//                    @Override
//                    public boolean apply(@Nullable WebDriver driver) {
//                        driver.findElement(By.cssSelector("button")).click();
//                        return true;
//                    }
//                });
//        driver.findElement(By.linkText("gchichvarkin")).click();
//        driver.findElement(By.linkText("Log Out")).click();
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
