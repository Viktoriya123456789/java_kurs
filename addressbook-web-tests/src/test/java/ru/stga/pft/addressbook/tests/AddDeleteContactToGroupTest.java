package ru.stga.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;
import ru.stga.pft.addressbook.model.GroupData;




/**
 * Created by admin on 24.07.2017.
 */
    public class AddDeleteContactToGroupTest extends TestBase {

    @BeforeMethod
    public void ensureGroupPreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test1").withFooter("test2"));
        }
    }

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().goToPage();
            app.contact().create(new ContactData()
                    .withFirstName("test").withLastName("test1").withAddress("test2")
                    .withWorkPhone("123").withHomePhone("456").withMobilePhone("789")
                    .withEmail1("test@test").withEmail2("test1@test").withEmail3("test3@test"));
        }
    }

    @Test
    public void testAddContactToGroup() {
        GroupData group = app.db().getNewGroup();
        ContactData contact = app.db().getNewContact();


        app.contact().selectContact(contact.getId());
        app.contact().selectGroupById(group.getId());
        app.contact().addContactToGroup(group.getId());

        app.goTo().groupPage();
        GroupData groupData = new GroupData().withName("test5").withHeader("test3").withFooter("test3");
        app.group().create(groupData);

        GroupData group3 = app.db().getNewGroup();
        app.goTo().goToPage();
        app.contact().selectContact(contact.getId());
        app.contact().selectGroupById(group3.getId());
        app.contact().addContactToGroup(group3.getId());




        for (GroupData groupToDelete : app.db().groups()){
            app.goTo().goToPage();
            app.contact().selectGroupByIdForDelete(groupToDelete.getId());
            app.contact().selectContact(contact.getId());
           // app.contact().deleteContactToGroup(groupToDelete.getId());
            app.contact().removeContactFromGroup(groupToDelete.getId());
        }

   }

}