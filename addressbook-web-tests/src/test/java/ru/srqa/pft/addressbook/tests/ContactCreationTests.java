package ru.srqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.srqa.pft.addressbook.model.ContactData;
import ru.srqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("Karina").withLastname("Kuznetsova").withAddress("Saint-Petersburg").withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withEmail("email@email.com");
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test (enabled = false)
  public void testBadContactCreation() throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("Karina'").withLastname("Kuznetsova").withAddress("Saint-Petersburg").withMobilePhone("80001112233").withEmail("email@email.com");
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }

}
