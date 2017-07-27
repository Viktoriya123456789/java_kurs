package ru.stga.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
 import org.testng.annotations.Test;
 import ru.stga.pft.addressbook.model.ContactData;
 import ru.stga.pft.addressbook.model.Contacts;
 import ru.stga.pft.addressbook.model.GroupData;
 import ru.stga.pft.addressbook.model.Groups;


/**
 * Created by admin on 27.07.2017.
 */
    public class ADD extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test1").withHeader("test1").withFooter("test1"));
        }

        app.goTo().goToPage();
        Groups groups = app.db().groups();
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstName("test").withLastName("test1").withAddress("test2")
                    .withWorkPhone("123").withHomePhone("456").withMobilePhone("789")
                    .withEmail1("test@test").withEmail2("test1@test").withEmail3("test3@test"));

        }
    }

    @Test
    public void testAddContactToGroup() {
        int contactId = 0;
        boolean completed = false;
        Groups beforeAdditionGroups = null;
        Groups beforeAddedGroups = null;
        Groups existedGroups = app.db().groups();
        Contacts contacts = app.db().contacts();

        for (ContactData editedContact : contacts) {
            beforeAdditionGroups = editedContact.getGroups();
            GroupData newGroup = app.contact().getGroupToAddition(existedGroups, editedContact);
            if (newGroup != null) {
                app.contact().addition(editedContact, newGroup);
                contactId = editedContact.getId();
                beforeAddedGroups = beforeAdditionGroups.withAdded(newGroup);
                completed = true;
                break;
            }
        }
        if (!completed) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test2").withHeader("test2").withFooter("test2"));
            Groups extendedGroups = app.db().groups();
            GroupData lastAddedGroup = extendedGroups.stream()
                    .max((g1, g2) -> Integer.compare(g1.getId(), g2.getId())).get();
            ContactData contact = contacts.iterator().next();
            contactId = contact.getId();
            app.contact().addition(contact, lastAddedGroup);
            beforeAddedGroups = beforeAdditionGroups.withAdded(lastAddedGroup);

        }

        Groups groupAfter = app.db().contactById(contactId).iterator().next().getGroups();
        MatcherAssert.assertThat(groupAfter, CoreMatchers.equalTo(beforeAddedGroups));
    }
}