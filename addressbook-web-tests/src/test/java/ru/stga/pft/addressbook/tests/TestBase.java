package ru.stga.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.appmanager.ApplicationManager;

/**
 * Created by admin on 23.06.2017.
 */
public class TestBase {

    protected final ApplicationManager app = new ApplicationManager();
      FirefoxDriver wd;

    @BeforeMethod
    public void setUp() throws Exception {
        app.init();
    }

    @Test
    public void testGroupDeletion () {
        wd.get("http://localhost:8080/addressbook/group.php");
        wd.findElement(By.name("user")).click();
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys("admin");
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys("secret");
        wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
        wd.findElement(By.linkText("groups")).click();
        wd.findElement(By.name("selected[]")).click();
        wd.findElement(By.xpath("//div[@id='content']/form/input[5]")).click();
        wd.findElement(By.linkText("group page")).click();
    }

    @Test
    public void testContactDeletion () {
        wd.get("http://localhost:8080/addressbook/group.php");
        wd.findElement(By.name("user")).click();
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys("admin");
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys("secret");
        wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
        wd.findElement(By.linkText("home")).click();
        wd.findElement(By.id("1")).click();
        wd.findElement(By.xpath("//div[@id='content']/form[2]/div[2]/input")).click();
    }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }

}
