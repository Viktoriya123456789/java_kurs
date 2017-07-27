package ru.stga.pft.addressbook.appmanager;

import org.hibernate.sql.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stga.pft.addressbook.model.ContactData;
import ru.stga.pft.addressbook.model.Contacts;
import ru.stga.pft.addressbook.model.GroupData;
import ru.stga.pft.addressbook.model.Groups;

import java.util.List;

import static ru.stga.pft.addressbook.tests.TestBase.app;


/**
 * Created by admin on 27.06.2017.
 */
public class ContactHelper extends HelperBase {


    public ContactHelper(WebDriver wd) {
        super(wd);
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


    public void initContactModification(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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


    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void create(ContactData contact) {
        initContactCreation(By.linkText("add new"));
        fillContactForm(contact, creation);
        submitContactCreation();
        contactCache = null;
        returnToContactPage();
    }

    public void modify(ContactData contact) {
        initContactModification(contact.getId());
        fillContactForm(contact, creation);
        updateContactModification();
        contactCache = null;
        returnToContactPage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        isAlertPresent();
        contactCache = null;
        returnToContactPage();
    }

    private Contacts contactCache = null;

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("email"), contactData.getEmail1());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        //type(By.name("allEmailes"), contactData.getAllEmailes());
        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public ContactData infoFromEditForm(ContactData contact, boolean creation) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        // String emailes = wd.findElement(By.name("emailes")).getAttribute("value");
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withAddress(address)
                .withEmail1(email1).withEmail2(email2).withEmail3(email3);
        //.withAllEmailes(emailes);

    }

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmailes = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            //String email1 = cells.get(4).getText();
            //String email2 = cells.get(4).getText();
            //String email3 = cells.get(4).getText();


            contactCache.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname)
                    .withAllPhones(allPhones)
                    .withAddress(address)
                    //   .withEmail1("email1").withEmail2("email2").withEmail3("email3"));
                    .withAllEmailes(allEmailes));

        }
        return new Contacts(contactCache);
    }

    private void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public void selectContact(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }


    public void addition(ContactData contact, GroupData group) {
        app.goTo().goToPage();
        selectContactById(contact.getId());
        addContactToGroup(group);
        contactCache = null;
        app.goTo().goToPage();
    }

    private void addContactToGroup(GroupData group) {
        new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(group.getId()));
        click(By.xpath("//div[@id='content']/form[2]/div[4]/input"));
        //click(By.cssSelector("input[name='add']"));
    }

    public GroupData getGroupToAddition(Groups groups, ContactData contact) {
        Groups beforeAdditionGroups = contact.getGroups();
        for (GroupData group : groups) {
            if (!beforeAdditionGroups.contains(group)) {
                return group;
            }
        }
        return null;
    }


    public void deleteFromGroup(ContactData contact, GroupData group) {
        app.goTo().goToPage();
        SelectedGroupById(String.valueOf(group.getId()));
        selectContactById(contact.getId());
        click(By.name("remove"));
        app.goTo().goToPage();
        SelectedGroupById("");
        contactCache = null;
        app.goTo().goToPage();
    }


    private void SelectedGroupById(String id) {
        new Select(wd.findElement(By.name("group"))).selectByValue(id);
        //wd.findElement(By.xpath("//select[@name='to_group']//option[@value='" + id + "']")).click();
    }



    }



















