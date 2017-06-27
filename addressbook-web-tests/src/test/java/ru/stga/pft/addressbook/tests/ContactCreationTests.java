package ru.stga.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;


public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("Viktoriya", "Yanuzakova", "Kazakhstan", "7271111111", "test@test.ru"));
        app.returnToContactPage();
}

}

