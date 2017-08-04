package ru.stga.pft.mantis.tests;


import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.hibernate.service.spi.ServiceException;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stga.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    Logger loger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();

    }

    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException, javax.xml.rpc.ServiceException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    public boolean isIssueOpen(int issueId) throws RemoteException, MalformedURLException, ServiceException, javax.xml.rpc.ServiceException {
        MantisConnectPortType mc = new MantisConnectLocator().getMantisConnectPort(new URL("http://localhost:8080/mantisbt-1.2.19/api/soap/mantisconnect.php"));
        IssueData fixedIssueData = mc.mc_issue_get("Administrator", "root", BigInteger.valueOf(issueId));
        if (fixedIssueData.getResolution().getName().equals("fixed")){
            return false;
        } else {
            return true;
        }
    }

}