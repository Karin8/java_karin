package ru.srqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.srqa.pft.addressbook.model.ContactData;

public class ContactEditTests extends TestBase {
  @Test
  public void testContactEdit() throws Exception {
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().initContactEdit();
    app.getContactHelper().fillContactForm(new ContactData("test1", "test2", "test3", "test4", "test5"));
    app.getContactHelper().submitContactEdit();
    app.getNavigationHelper().waitForSuccessMessage();
    app.getNavigationHelper().goToHomePage();

  }

}
