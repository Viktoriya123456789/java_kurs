package ru.stga.pft.addressbook.tests;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ContactDeletionTests extends TestBase {


    @Test
    public void testContactDeletion() {
        app.getContactHelper().goToPage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().isAlertPresent();
        app.getContactHelper().returnToContactPage();
    }
}