package ru.stga.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;
import ru.stga.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by admin on 27.06.2017.
 */
public class ContactModificationTests extends TestBase {



    @Test
    public void testContactModification() {
        app.getContactHelper().goToPage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("test", "test1", "test2"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification(before.size() -1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"test", "test1", "test2");
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().updateContactModification();
        app.getContactHelper().returnToContactPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare (c1.getId(), c2.getId());
        before.sort(byId);
        after.sort (byId);
        Assert.assertEquals(before, after);
    }
}