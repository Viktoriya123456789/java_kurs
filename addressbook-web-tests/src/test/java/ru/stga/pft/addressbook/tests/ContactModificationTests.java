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
        app.goTo().goToPage();
        if (app.group().all().size() == 0){
            app.contact().create(new ContactData().withFirstName("test").withLastName("test1").withAddress("test2"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                    .withId(modifiedContact.getId()).withFirstName("test").withLastName("test1").withAddress("test2");
        app.contact().modify(contact);
        assertThat(app.group().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}