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

public class ORDERLIST01 {
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
  public void testORDERLIST01() throws Exception {
    driver.get("https://hypershop-scys.onrender.com/admin/manage-cart");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Không đổi'])[1]/following::div[2]")).click();
    driver.findElement(By.id("k-52b8306b-0271-4e85-a00d-f81c7e90c09e")).click();
    driver.findElement(By.xpath("//input[@type='checkbox']")).click();
    driver.findElement(By.id("k-4c1ce7bd-1c0a-4272-a9a5-9892fc2b20b6")).click();
    driver.findElement(By.id("k-4c1ce7bd-1c0a-4272-a9a5-9892fc2b20b6")).clear();
    driver.findElement(By.id("k-4c1ce7bd-1c0a-4272-a9a5-9892fc2b20b6")).sendKeys("Lương Hoài Nam");
    driver.findElement(By.id("k-4c1ce7bd-1c0a-4272-a9a5-9892fc2b20b6")).sendKeys(Keys.ENTER);
    //Warning: verifyTextPresent may require manual changes
    try {
      assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Lương Hoài Nam[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
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
