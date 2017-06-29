package ru.stga.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stga.pft.addressbook.model.ContactData;
import ru.stga.pft.addressbook.model.GroupData;

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
        type(By.name("home"), contactData.getTelephone());
        type(By.name("email"), contactData.getEmail());
        //click(By.xpath("//div[@id='content']/form/input[21]"));
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

    public void initContactModification() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
        //click(By.name("edit"));
    }


    public void selectContact() {
        click(By.name("selected[]"));

    }

    public void updateContactModification() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
        //click(By.name("update"));
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
    //            click(By.name("submit"));
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


        private boolean isElementPresent(By name) {
            return false;
        }

    public boolean isThereAContact() {
        return isElementPresent (By.name("selected[]"));
    }
}


