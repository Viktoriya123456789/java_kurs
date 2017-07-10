package ru.stga.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by admin on 27.06.2017.
 */
public class ContactModificationTests extends TestBase {



    @Test
    public void testContactModification() {
        app.getContactHelper().goToPage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("firstname", "lastname", "address"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification(before.size() -1);
        app.getContactHelper().fillContactForm(new ContactData("firstname", "lastname", "address"));
        app.getContactHelper().updateContactModification();
        app.getContactHelper().returnToContactPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
    }
}