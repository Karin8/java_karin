package ru.srqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.srqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().createGroup(new GroupData("test1", null, null));
  }

}
