package ru.srqa.pft.addressbook;

import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {
    selectItem();
    deleteSelectedItems();
    confirmDeletion();
    waitForTransferToHomepage();
  }


}
