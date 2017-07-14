package ru.stga.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;
import ru.stga.pft.addressbook.model.Contacts;
import java.util.Set;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {
        app.goTo().goToPage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstName("test").withLastName("test1").withAddress("test2");
        app.contact().create(contact);
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadContactCreation() {
        app.goTo().goToPage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstName("test'").withLastName("test1'").withAddress("test2'");
        app.contact().create(contact);
        assertThat(app.group().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }

}





