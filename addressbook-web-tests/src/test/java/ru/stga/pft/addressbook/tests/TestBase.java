package ru.stga.pft.addressbook.tests;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
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


    @AfterMethod
    public void tearDown() {
        app.stop();
    }

}
