package ru.srqa.pft.addressbook;

import org.testng.annotations.*;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {
    selectContact();
    deleteSelectedContact();
    confirmDeletion();
    waitForTransferToHomepage();
  }


}