package com.example.Module3;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import java.io.File;

public class ORDERSTATUS01 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  JavascriptExecutor js;
  @Before
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "");
    driver = new ChromeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void testORDERSTATUS01() throws Exception {
    driver.get("https://hypershop-scys.onrender.com/admin/manage-cart");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Không đổi'])[1]/following::span[1]")).click();
    driver.findElement(By.id("k-80820c65-c9c8-424e-a76b-c22c1afdc7e1")).click();
    driver.findElement(By.xpath("//input[@type='checkbox']")).click();
    driver.findElement(By.xpath("//input[@type='checkbox']")).click();
    driver.findElement(By.xpath("//div[@id='k-e40b8f30-0312-4cb6-b9c6-e8843f4c4907']/kendo-grid-list/div/div/table/tbody/tr/td[9]/div/div/component-button/div/i")).click();
    driver.findElement(By.xpath("//div[@id='k-e40b8f30-0312-4cb6-b9c6-e8843f4c4907']/kendo-grid-list/div/div/table/tbody/tr/td[9]/div/div[2]/component-dropdown-action/div/div[3]/component-button/div/span")).click();
    //ERROR: Caught exception [unknown command [verifyTextPresent ]]
    driver.findElement(By.xpath("//div[2]/div[2]/component-button/div/i")).click();
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
