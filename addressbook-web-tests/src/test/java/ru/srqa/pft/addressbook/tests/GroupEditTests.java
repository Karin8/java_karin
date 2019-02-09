package ru.srqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.srqa.pft.addressbook.model.GroupData;

public class GroupEditTests extends TestBase {

  @Test
  public void testGroupEdit() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupEdit();
    app.getGroupHelper().fillGroupForm(new GroupData("test4", "test5", "test6"));
    app.getGroupHelper().submitGroupEdit();
    app.getNavigationHelper().goToGroupPage();

  }
}
