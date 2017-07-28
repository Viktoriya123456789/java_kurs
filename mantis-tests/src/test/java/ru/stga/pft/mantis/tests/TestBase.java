package ru.stga.pft.mantis.tests;


import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stga.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;

/**
 * Created by admin on 23.06.2017.
 */
public class TestBase {

    public static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_int.php"), "config_int.php", "config_int.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("config_int.php.bak", "config_int.php");
        app.stop();
    }
}