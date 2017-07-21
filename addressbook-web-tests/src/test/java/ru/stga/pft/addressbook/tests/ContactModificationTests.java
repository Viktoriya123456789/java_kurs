package ru.stga.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;
import ru.stga.pft.addressbook.model.Contacts;
import ru.stga.pft.addressbook.model.GroupData;
import ru.stga.pft.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by admin on 27.06.2017.
 */
public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().contacts().size() == 0){
            app.goTo().goToPage();
            app.contact().create(new ContactData().withFirstName("test").withLastName("test1").withAddress("test2")
                    .withWorkPhone("123").withHomePhone("456").withMobilePhone("789")
                    .withEmail1("test@test").withEmail2("test1@test").withEmail3("test3@test"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                    .withId(modifiedContact.getId()).withFirstName("test").withLastName("test1").withAddress("test2")
                .withWorkPhone("123").withHomePhone("456").withMobilePhone("789")
                .withEmail1("test@test").withEmail2("test1@test").withEmail3("test3@test");

        app.goTo().goToPage();
        app.contact().modify(contact);
        assertThat(app.group().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}