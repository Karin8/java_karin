package ru.srqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.srqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroup();
    app.getNavigationHelper().goToGroupPage();
  }

}
