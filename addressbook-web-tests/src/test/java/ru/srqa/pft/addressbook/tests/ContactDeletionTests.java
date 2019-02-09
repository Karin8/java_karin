package ru.srqa.pft.addressbook.tests;

import org.testng.annotations.*;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().confirmContactDeletion();
    app.getNavigationHelper().waitForSuccessfulDeletionMessage();
    app.getNavigationHelper().returnToHomePage();
  }


}
