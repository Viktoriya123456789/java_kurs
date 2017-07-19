package ru.stga.pft.addressbook.tests;



import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stga.pft.addressbook.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by admin on 23.06.2017.
 */
public class TestBase {

    org.slf4j.Logger logger = LoggerFactory.getLogger(GroupCreationTests.class);


    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));


    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }


    @AfterSuite
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod (alwaysRun = true)
    public void logTestStart(Method m, Object[] p){
        logger.info("Start test " + m.getName() + "with parameters " + Arrays.asList(p));

    }

    @AfterMethod (alwaysRun = true)
        public void logTestStop(Method m){
        logger.info("Stop test " +m.getName());
    }
}
