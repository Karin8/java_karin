package ru.srqa.pft.addressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactCreationTests {
  private WebDriver wb;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wb = new FirefoxDriver();
    wb.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wb.get("http://localhost/addressbook/");
    login();
  }

  private void login() {
    wb.findElement(By.name("user")).click();
    wb.findElement(By.name("user")).clear();
    wb.findElement(By.name("user")).sendKeys("admin");
    wb.findElement(By.name("pass")).click();
    wb.findElement(By.name("pass")).clear();
    wb.findElement(By.name("pass")).sendKeys("secret");
    wb.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password:'])[1]/following::input[2]")).click();
  }

  @Test
  public void testContactCreation() throws Exception {
    initContactCreation();
    fillContactForm();
    submitContactCreation();
    returnToHomePage();
  }

  private void fillContactForm() {
    wb.findElement(By.name("firstname")).click();
    wb.findElement(By.name("firstname")).clear();
    wb.findElement(By.name("firstname")).sendKeys("Karina");
    wb.findElement(By.name("lastname")).click();
    wb.findElement(By.name("lastname")).clear();
    wb.findElement(By.name("lastname")).sendKeys("Kuznetsova");
    wb.findElement(By.name("address")).click();
    wb.findElement(By.name("address")).clear();
    wb.findElement(By.name("address")).sendKeys("Saint-Petersburg");
    wb.findElement(By.name("mobile")).click();
    wb.findElement(By.name("mobile")).clear();
    wb.findElement(By.name("mobile")).sendKeys("80001112233");
    wb.findElement(By.name("email")).click();
    wb.findElement(By.name("email")).clear();
    wb.findElement(By.name("email")).sendKeys("email@email.com");
  }

  private void returnToHomePage() {
    wb.findElement(By.linkText("home")).click();
  }

  private void submitContactCreation() {
    wb.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]")).click();
  }

  private void initContactCreation() {
    wb.findElement(By.linkText("add new")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    wb.findElement(By.linkText("Logout")).click();
    wb.quit();
  }

  private boolean isElementPresent(By by) {
    try {
      wb.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wb.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
