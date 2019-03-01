package ru.srqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.srqa.pft.addressbook.model.ContactData;

import java.util.List;

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
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before, after);
  }

  @Test
  public void testContactDeletionFromEditPage() throws Exception {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().deleteViaEdit(index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before, after);
  }

}
