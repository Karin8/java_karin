package ru.srqa.pft.addressbook.tests;

import org.testng.annotations.*;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletionFromHomePage() throws Exception {
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().confirmContactDeletion();
    app.getNavigationHelper().waitForSuccessMessage();
    app.getNavigationHelper().goToHomePage();
  }

  @Test
  public void testContactDeletionFromEditPage() throws Exception {
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().initContactEdit();
    app.getContactHelper().deleteSelectedContact();
    app.getNavigationHelper().waitForSuccessMessage();
    app.getNavigationHelper().goToHomePage();
  }

}
