package ru.stga.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;

/**
 * Created by admin on 27.06.2017.
 */
public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getContactHelper().returnToContactPage();
       app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Viktoriya11", "Yanuzakova", "Kazakhstan", "7271111111", "test@test.ru"));
        app.getContactHelper().updateContactModification();
        app.getContactHelper().returnToContactPage();
    }
}