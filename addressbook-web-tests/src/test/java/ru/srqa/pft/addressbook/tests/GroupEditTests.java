package ru.srqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.srqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupEditTests extends TestBase {

  @Test
  public void testGroupEdit() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().initGroupEdit();
    GroupData group = new GroupData(before.get(before.size() - 1).getId(), "test4", "test5", "test6");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupEdit();
    app.getNavigationHelper().goToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

  }
}
