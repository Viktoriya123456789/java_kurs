package ru.stga.pft.addressbook.tests;


import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.Contacts;
import ru.stga.pft.addressbook.model.GroupData;
import ru.stga.pft.addressbook.model.Groups;

/**
 * Created by admin on 27.07.2017.
 */
public class DELETE extends TestBase {
    ContactData testContact = new ContactData();
    GroupData testGroup = new GroupData();


    @BeforeMethod
    public void ensurePreconditions() {
        Groups group = app.db().groups();

        Groups allGroups = app.db().groups();
        if (allGroups.size() == 0) {
            GroupData newGroup = new GroupData().withName(RandomStringUtils.randomAlphabetic(10));
            app.goTo().groupPage();
            ;
            app.group().create(newGroup);
            allGroups = app.db().groups();
        }
        Contacts contacts = app.db().contacts();
        if (contacts.size() != 0) {
            try {
                testContact = contacts.stream().filter((c) -> c.getGroups().size() != 0).iterator().next();
            } catch (Exception ex) {
                testContact = null;
            }
            if (testContact == null) {
                ContactData contact = contact.iterator().next();
                GroupData randomGroup = group.iterator().next();
                app.contact().addition(contact, randomGroup);
                contacts = app.db().contacts();
                testContact = contacts.stream().filter((c) -> c.getGroups().size() != 0).iterator().next();
            }
        } else {
            ContactData newContact = new ContactData()
                    .withFirstName(RandomStringUtils.randomAlphabetic(10))
                    .withLastName(RandomStringUtils.randomAlphabetic(10))
                    .withAddress(RandomStringUtils.randomAlphabetic(10))
                    .withWorkPhone(RandomStringUtils.randomAlphabetic(10))
                    .withHomePhone(RandomStringUtils.randomAlphabetic(10))
                    .withMobilePhone(RandomStringUtils.randomAlphabetic(10))
                    .withEmail1(RandomStringUtils.randomAlphabetic(10))
                    .withEmail2(RandomStringUtils.randomAlphabetic(10))
                    .withEmail3(RandomStringUtils.randomAlphabetic(10)).inGroup(allGroups.stream().iterator().next());
            app.goTo().goToPage();
            app.contact().create(new ContactData()
                    .withFirstName("test").withLastName("test1").withAddress("test2")
                    .withWorkPhone("123").withHomePhone("456").withMobilePhone("789")
                    .withEmail1("test@test").withEmail2("test1@test").withEmail3("test3@test")).inGroup(allGroups.stream().iterator().next());

            testContact = app.db().contacts().stream().filter((c) -> (c.getGroups().size() != 0)).iterator().next();
        }
        Groups groupsWithContact = testContact.getGroups();
        testGroup = groupsWithContact.stream().iterator().next();

    }

    @Test
    public void testDeletionContactFromGroup() {

        ContactData before = app.db().contacts().stream().filter((c) -> (c.getId() == testContact.getId())).iterator().next();
        app.contact().deleteFromGroup(testContact, testGroup);
        ContactData after = app.db().contacts().stream().filter((c) -> (c.getId() == testContact.getId())).iterator().next();
        System.out.println("before: " + before);
        System.out.println("after: " + after);
        org.junit.Assert.assertThat(before, CoreMatchers.equalTo(after.inGroup(testGroup)));
    }

}

