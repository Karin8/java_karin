package ru.srqa.pft.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.srqa.pft.addressbook.model.ContactData;
import ru.srqa.pft.addressbook.model.Contacts;
import ru.srqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {

    super(wd);
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));

  }


  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail1());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
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


  public ContactData infoFromEditForm(ContactData contact) {
    initContactEditById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email1 = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withAddress(address).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withEmail1(email1).withEmail2(email2).withEmail3(email3);
  }

  public void initContactEditById(int id) {
    click(By.cssSelector("a[href='edit.php?id=" + id + "']"));
  }

  public void submitContactEdit() {
    click(By.name("update"));
  }
  public void returnToHomePage() {
    click(By.linkText("home"));
  }

  public void create(ContactData contact) {
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    initContactEditById(contact.getId());
    fillContactForm(contact, false);
    submitContactEdit();
    waitForSuccessMessage();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    confirmContactDeletion();
    waitForSuccessMessage();
    contactCache = null;
    returnToHomePage();
  }

  public void deleteViaEdit(ContactData contact) {
    initContactEditById(contact.getId());
    deleteSelectedContact();
    waitForSuccessMessage();
    contactCache = null;
    returnToHomePage();
  }

  public void addContactToGroup(ContactData contact, GroupData group) {
    selectContactById(contact.getId());
    selectGroupById(group.getId());
    confirmContactToGroupAdding();
    contactCache = null;
    returnToHomePage();
  }

  private void confirmContactToGroupAdding() {
    click(By.xpath("//input[@name='add']"));
  }

  private void selectGroupById(int id) {

    click(By.cssSelector("select[name=\"to_group\"] > option[value='" + id + "']"));
  }

  public void removeContactFromGroup(ContactData contact, GroupData group) {
    selectGroupListById(group.getId());
    selectContactById(contact.getId());
    initContactRemovingFromGroup();
    contactCache = null;
    returnToHomePage();
  }

  private void selectGroupListById(int id) {
    click(By.cssSelector("select[name=\"group\"] > option[value='" + id + "']"));
  }


  private void initContactRemovingFromGroup() {
    click(By.name("remove"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//img[@alt='Edit']"));
  }

  public void waitForSuccessMessage() {
    wd.findElement(By.cssSelector(".msgbox"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element: elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String firstname = element.findElement(By.xpath(".//td[3]")).getText();
      String lastname = element.findElement(By.xpath(".//td[2]")).getText();
      String address = element.findElement(By.xpath(".//td[4]")).getText();
      String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
      String allEmails = element.findElement(By.xpath(".//td[5]")).getText();
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAddress(address)
              .withAllPhones(allPhones).withAllEmails(allEmails));
    }
    return new Contacts(contactCache);
  }

}
