package ru.srqa.pft.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.srqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {

    super(wd);
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));

  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("email"), contactData.getEmail());
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void initContactCreation() {

    click(By.linkText("add new"));
  }

  public void confirmContactDeletion() {

    wd.switchTo().alert().accept();
  }
  public void initContactEdit(int index) {
      wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }
  public void submitContactEdit() {
    click(By.name("update"));
  }
  public void returnToHomePage() {
    click(By.linkText("home"));
  }

  public void create(ContactData contact) {
    initContactCreation();
    fillContactForm(contact);
    submitContactCreation();
    returnToHomePage();
  }

  public void modify(int index, ContactData contact) {
    initContactEdit(index);
    fillContactForm(contact);
    submitContactEdit();
    waitForSuccessMessage();
    returnToHomePage();
  }

  public void delete(int index) {
    selectContact(index);
    deleteSelectedContact();
    confirmContactDeletion();
    waitForSuccessMessage();
    returnToHomePage();
  }

  public void deleteViaEdit(int index) {
    initContactEdit(index);
    deleteSelectedContact();
    waitForSuccessMessage();
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//img[@alt='Edit']"));
  }

  public void waitForSuccessMessage() {
    wd.findElement(By.cssSelector(".msgbox"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element: elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String firstname = element.findElement(By.xpath(".//td[3]")).getText();
      String lastname = element.findElement(By.xpath(".//td[2]")).getText();
      ContactData contact = new ContactData(id, firstname, lastname, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}
