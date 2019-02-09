package ru.srqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    goToGroupPage();
    selectItem();
    deleteSelectedItems();
    returnToGroupPage();
  }

}
