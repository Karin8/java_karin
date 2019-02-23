package ru.srqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.srqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactEditTests extends TestBase {
  @Test
  public void testContactEdit() throws Exception {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Karina", "Kuznetsova", "Saint-Petersburg", "80001112233", "email@email.com"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactEdit();
    app.getContactHelper().fillContactForm(new ContactData("test1", "test2", "test3", "test4", "test5"));
    app.getContactHelper().submitContactEdit();
    app.getNavigationHelper().waitForSuccessMessage();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

  }

}
