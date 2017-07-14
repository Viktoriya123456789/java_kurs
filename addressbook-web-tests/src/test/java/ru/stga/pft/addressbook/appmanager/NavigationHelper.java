package ru.stga.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by admin on 27.06.2017.
 */
public class NavigationHelper extends HelperBase {


    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {
        click(By.linkText("groups"));
    }

    public void goToPage() {
        click(By.linkText("home"));

    }

    public void gotoHomePage() {
        click(By.linkText("home"));
    }
}
