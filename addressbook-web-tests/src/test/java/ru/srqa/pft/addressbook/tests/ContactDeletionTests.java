package ru.srqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.srqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().
              withFirstname("Karina").withLastname("Kuznetsova").withAddress("Saint-Petersburg").withMobilePhone("80001112233").withEmail("email@email.com"));
    }
  }

  @Test
  public void testContactDeletionFromHomePage() throws Exception {
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }

  @Test
  public void testContactDeletionFromEditPage() throws Exception {
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().deleteViaEdit(deletedContact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }

}
