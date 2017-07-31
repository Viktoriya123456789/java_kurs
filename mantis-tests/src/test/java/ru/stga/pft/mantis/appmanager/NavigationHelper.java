package ru.stga.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class NavigationHelper extends HelperBase {

    private WebDriver wd;
    private WebDriverWait wait;

    public NavigationHelper(ApplicationManager app) {
        super(app);
        wd = app.getDriver();
        wait = new WebDriverWait(wd, 5);
    }


    public void goToHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home page"));
    }

    public void manage() {
        click(By.xpath("//li[6]/a/span")); //пока не используется
    }

    public void login(String username, String password) {
        wd.manage().window().maximize();
        wd.get("http://localhost:8080/mantisbt-1.2.19/login_page.php");
        By user = By.cssSelector("input[name=username]");
        By pass = By.cssSelector("input[name=password]");
        wd.findElement(user);
        click(user);
        type(user, "administrator");
        wd.findElement(By.cssSelector("input[type=submit]")).click();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.findElement(pass);
        click(pass);
        type(pass, "root");
        wd.findElement(By.cssSelector("input[type=submit]")).click();

    }

    public void usersManage() {
        click(By.linkText("Manage Users"));

    }


}