package ru.srqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.srqa.pft.addressbook.model.ContactData;
import ru.srqa.pft.addressbook.model.Contacts;
import ru.srqa.pft.addressbook.model.GroupData;
import ru.srqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemovingContactFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      Groups groups = app.db().groups();
      app.contact().create(new ContactData()
              .withFirstname("Karina").withLastname("Kuznetsova").withAddress("Saint-Petersburg").withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withEmail1("email@email.com").inGroup(groups.iterator().next()));
    }
    if (app.db().groups().iterator().next().getContacts().size() == 0) {
      Groups groupList = app.db().groups();
      GroupData toGroup = groupList.iterator().next();
      Contacts contacts = app.db().contacts();
      ContactData selectedContact = contacts.iterator().next();
      app.contact().addContactToGroup(selectedContact, toGroup);
    }
  }

  @Test
  public void testRemovingContactFromGroup() {
    app.goTo().homePage();
    Contacts contactsBefore = app.db().contacts();
    Groups groupList = app.db().groups();
    ContactData selectedContact = contactsBefore.iterator().next();
    GroupData fromGroup = groupList.iterator().next();
    app.contact().removeContactFromGroup(selectedContact, fromGroup);
    Contacts contactsAfter = app.db().contacts();
    assertThat(contactsAfter.iterator().next().getGroups(), equalTo(contactsBefore.iterator().next().getGroups().without(fromGroup)));
    app.contact().goBackToAll();
    verifyContactListInUi();
  }
}
