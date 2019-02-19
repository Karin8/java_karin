package ru.srqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.srqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletionFromHomePage() throws Exception {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Karina", "Kuznetsova", "Saint-Petersburg", "80001112233", "email@email.com"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().confirmContactDeletion();
    app.getNavigationHelper().waitForSuccessMessage();
    app.getNavigationHelper().goToHomePage();
  }

  @Test
  public void testContactDeletionFromEditPage() throws Exception {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Karina", "Kuznetsova", "Saint-Petersburg", "80001112233", "email@email.com"));
    }
    app.getContactHelper().initContactEdit();
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().waitForSuccessMessage();
    app.getNavigationHelper().goToHomePage();
  }

}
