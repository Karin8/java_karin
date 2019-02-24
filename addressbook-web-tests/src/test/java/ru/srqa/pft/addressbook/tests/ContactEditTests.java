package ru.srqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.srqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactEditTests extends TestBase {
  @Test
  public void testContactEdit() throws Exception {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Karina", "Kuznetsova", "Saint-Petersburg", "80001112233", "email@email.com"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactEdit(before.size() - 1);
    ContactData contact = new ContactData("test1", "test2", "test3", "test4", "test5");
    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().submitContactEdit();
    app.getNavigationHelper().waitForSuccessMessage();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
