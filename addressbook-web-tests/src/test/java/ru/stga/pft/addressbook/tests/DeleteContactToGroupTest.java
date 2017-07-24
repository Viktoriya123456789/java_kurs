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
 * Created by admin on 24.07.2017.
 */
public class DeleteContactToGroupTest extends TestBase {

    @BeforeMethod(enabled = false)
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().isEmpty()) {
            app.group().create(new GroupData()
                    .withName("test")
                    .withFooter("test")
                    .withHeader("test"));
        }

        app.goTo().goToPage();
        if (app.contact().all().isEmpty()) {
            app.contact().create(new ContactData()
                    .withFirstName("test1")
                    .withLastName("test1")
                    .withAddress("test2")
                    .withWorkPhone("123").withHomePhone("456").withMobilePhone("789")
                    .withEmail1("test@test").withEmail2("test1@test").withEmail3("test3@test"));
        }

    }


    @Test
    public void testAddContactToGroup() {
        app.goTo().goToPage();
        if (app.db().contactInGroup().isEmpty()) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test2").withFooter("test2")
                    .withHeader("test2"));
            app.goTo().goToPage();

        }
        Contacts before = app.db().contacts();
        app.goTo().goToPage();
        Groups group = app.db().groups();
        ContactData modifiedContact = app.db().contactNotInGroup().iterator().next();
        GroupData addedGroup = group.iterator().next();
        app.contact().selectContact(modifiedContact.getId());
        app.contact().addContactToGroup(addedGroup.getId());
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(modifiedContact.inGroup(addedGroup))));
    }
}

