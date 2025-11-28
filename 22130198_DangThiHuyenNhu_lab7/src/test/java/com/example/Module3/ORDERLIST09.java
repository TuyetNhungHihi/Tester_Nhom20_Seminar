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

public class ORDERLIST09 {
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
  public void testORDERLIST09() throws Exception {
    driver.get("https://hypershop-scys.onrender.com/admin/manage-cart");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Không đổi'])[1]/following::span[1]")).click();
    driver.findElement(By.id("k-6b09c6cd-8e61-422e-b235-236f1bb5406c")).click();
    driver.findElement(By.xpath("//input[@type='checkbox']")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Lọc trạng thái'])[1]/following::div[2]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Lọc trạng thái'])[1]/following::kendo-svgicon[1]")).click();
    driver.findElement(By.xpath("//td[@id='136ce14a-8a8e-46e3-82e3-f2d5c40a75631720583444000']/span")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Lọc trạng thái'])[1]/following::kendo-svgicon[2]")).click();
    driver.findElement(By.id("kendo-calendarid-2header-")).click();
    driver.findElement(By.xpath("//kendo-calendar[@id='kendo-calendarid-2']/kendo-multiviewcalendar/kendo-calendar-header/span[3]/button/kendo-icon-wrapper/kendo-svgicon")).click();
    driver.findElement(By.xpath("//td[@id='80ce05da-e6eb-427c-b929-f484b1c87d401722174288245']/span")).click();
    driver.findElement(By.xpath("//td[@id='80ce05da-e6eb-427c-b929-f484b1c87d401720619088245']/span")).click();
    try {
      assertTrue(isElementPresent(By.xpath("//td")););
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
