package ru.srqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.srqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().createContact(new ContactData("Karina", "Kuznetsova", "Saint-Petersburg", "80001112233", "email@email.com"));
  }

}
