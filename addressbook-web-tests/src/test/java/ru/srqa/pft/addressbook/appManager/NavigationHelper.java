package ru.srqa.pft.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static net.bytebuddy.matcher.ElementMatchers.is;

public class NavigationHelper {
  private WebDriver wd;

  public NavigationHelper(WebDriver wd) {
    this.wd = wd;
  }

  public void goToGroupPage() {
    wd.findElement(By.linkText("groups")).click();
  }

  public void returnToHomePage() {
    wd.findElement(By.linkText("home")).click();
  }

  public void waitForSuccessfulDeletionMessage() {
    wd.findElement(By.cssSelector(".msgbox"));
  }
}
