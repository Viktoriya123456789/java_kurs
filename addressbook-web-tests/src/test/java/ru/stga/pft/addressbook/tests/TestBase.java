package ru.stga.pft.addressbook.tests;



import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stga.pft.addressbook.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;

/**
 * Created by admin on 23.06.2017.
 */
public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);


    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }


    @AfterSuite
    public void tearDown() {
        app.stop();
    }

}
