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

public class ORDERLIST08 {
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
  public void testORDERLIST08() throws Exception {
    driver.get("https://hypershop-scys.onrender.com/admin/manage-cart");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Không đổi'])[1]/following::span[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Lọc trạng thái'])[1]/following::div[1]")).click();
    driver.findElement(By.xpath("//input[@type='checkbox']")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Thay đổi mật khẩu'])[1]/following::div[2]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Lọc trạng thái'])[1]/following::kendo-svgicon[1]")).click();
    driver.findElement(By.xpath("//td[@id='828ee472-8cbd-4cca-935c-30e3a9c3574f1719805844000']/span")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Lọc trạng thái'])[1]/following::kendo-svgicon[2]")).click();
    driver.findElement(By.xpath("//td[@id='7bc5ac78-4253-4f05-b24b-1e1aa1c05fee1763646199688']/span")).click();
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
