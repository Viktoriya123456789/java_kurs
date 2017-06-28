package ru.stga.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stga.pft.addressbook.model.ContactData;

/**
 * Created by admin on 27.06.2017.
 */
public class ContactHelper extends HelperBase{

    public ContactHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getTelephone());
        type(By.name("email"), contactData.getEmail());
        click(By.xpath("//div[@id='content']/form/input[21]"));
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
        click(By.linkText("home page"));
    }
    public void initContactModification() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[5]/td[8]/a/img"));
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
        click(By.name("submit"));
    }


    public void deleteSelectedContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    //            click(By.name("submit"));
    }


}

