package ru.stga.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;
import ru.stga.pft.addressbook.model.Contacts;
import ru.stga.pft.addressbook.model.GroupData;
import ru.stga.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


/**
 * Created by admin on 27.07.2017.
 */
public class AddContactToGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test"));
        }

        app.goTo().contactPage();
        Groups groups = app.db().groups();
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstname("test")
                    .withLastname("lastname"));
//                    .withAddress("address")
//                    .withHomePhone("home")
//                    .withMobilePhone("mobile")
//                    .withWorkPhone("work")
//                    .withEmail1("email")
//                    .withEmail2("email2")
//                    .withEmail3("email3"));


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

            Groups groupAfter = app.db().contactById(contactId).iterator().next().getGroups();
            assertThat(groupAfter, equalTo(beforeAddedGroups));
        }
    }
}