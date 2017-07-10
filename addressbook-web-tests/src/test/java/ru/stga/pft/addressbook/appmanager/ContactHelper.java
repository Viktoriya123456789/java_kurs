package ru.stga.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stga.pft.addressbook.model.ContactData;
import ru.stga.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by admin on 27.06.2017.
 */
public class ContactHelper extends HelperBase{



    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        //type(By.name("home"), contactData.getTelephone());
        //type(By.name("email"), contactData.getEmail());
    }

    protected void type(By locator, String text) {
        click(locator);
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }

    public void initContactCreation(By locator) {
        click(locator);
    }


    public void returnToContactPage() {
        click(By.linkText("home"));
    }



    public void initContactModification(int index) {
        wd.findElements(By.cssSelector("img[alt='Edit']")).get(index).click();
    }

    public void selectContact(int index) {
        //click(By.name("selected[]"));
        //wd.findElements(By.cssSelector("img[alt='Edit']")).get(index).click();
       wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void updateContactModification() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert().accept();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void deleteSelectedContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }


    public void goToPage() {
        click(By.linkText("home"));

    }

    public void createContact(ContactData contact) {
        initContactCreation(By.linkText("add new"));
        fillContactForm(contact);
        submitContactCreation();
        returnToContactPage();
    }

    public boolean isThereAContact() {
        return isElementPresent (By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("td.center"));
        for (WebElement element : elements){
            String name = element.getText();
            ContactData contact = new ContactData ("firstname", "lastname", "address");
            contacts.add (contact);
        }
        return contacts;
    }

}


