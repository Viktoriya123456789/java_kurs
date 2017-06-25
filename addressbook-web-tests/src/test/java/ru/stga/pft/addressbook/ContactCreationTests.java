package ru.stga.pft.addressbook;

import org.testng.annotations.Test;


public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {
        wd.get("http://localhost:8080/addressbook/");

        initContactCreation();
        fillContactForm(new ContactData("Viktoriya", "Yanuzakova", "Kazakhstan", "7271111111", "test@test.ru"));
        returnToContactPage();
}

}
