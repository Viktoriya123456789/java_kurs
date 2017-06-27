package ru.stga.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stga.pft.addressbook.model.ContactData;

/**
 * Created by admin on 27.06.2017.
 */
public class ContactHelper {
    private FirefoxDriver wd;

    public ContactHelper(FirefoxDriver wd) {
        this.wd = wd;
    }

    public void fillContactForm(ContactData contactData) {
        wd.findElement(By.name("firstname")).click();
        wd.findElement(By.name("firstname")).clear();
        wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
        wd.findElement(By.name("lastname")).click();
        wd.findElement(By.name("lastname")).clear();
        wd.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
        wd.findElement(By.name("address")).click();
        wd.findElement(By.name("address")).clear();
        wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
        wd.findElement(By.name("home")).click();
        wd.findElement(By.name("home")).clear();
        wd.findElement(By.name("home")).sendKeys(contactData.getTelephone());
        wd.findElement(By.name("email")).click();
        wd.findElement(By.name("email")).clear();
        wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    public void initContactCreation() {

        wd.findElement(By.linkText("add new")).click();
    }
    public void returnToContactPage() {

        wd.findElement(By.linkText("home page")).click();
    }
}
