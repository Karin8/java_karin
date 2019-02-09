package ru.srqa.pft.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.srqa.pft.addressbook.model.GroupData;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void submitGroupCreation() {

    click(By.name("submit"));
  }

  public void submitGroupEdit() {

    click(By.name("update"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroup() {
    click(By.xpath("//input[@name='delete']"));

  }

  public void selectGroup() {
    click(By.name("selected[]"));
  }

  public void initGroupEdit() {
    click(By.xpath("(//input[@name='edit'])[2]"));
  }
}
