package ru.stga.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResetPasswordHelper extends HelperBase {
    private ApplicationManager app;
    private WebDriver wd;

    public ResetPasswordHelper(ApplicationManager app) {
        super(app);
        wd = app.getDriver();
    }

    public void select(String user) {
        click(By.linkText(user));
    }


    public void resert() {
        click(By.xpath("//input[@value='Reset Password']"));

    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath("//form[@action='account_update.php']//input[@type='submit']"));

    }
}