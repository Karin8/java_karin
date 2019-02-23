package ru.srqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.srqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletionFromHomePage() throws Exception {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Karina", "Kuznetsova", "Saint-Petersburg", "80001112233", "email@email.com"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().confirmContactDeletion();
    app.getNavigationHelper().waitForSuccessMessage();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }

  @Test
  public void testContactDeletionFromEditPage() throws Exception {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Karina", "Kuznetsova", "Saint-Petersburg", "80001112233", "email@email.com"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactEdit();
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().waitForSuccessMessage();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }

}
