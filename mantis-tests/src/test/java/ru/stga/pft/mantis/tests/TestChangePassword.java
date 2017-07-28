package ru.stga.pft.mantis.tests;

/**
 * Created by admin on 28.07.2017.
 */
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestChangePassword extends TestBase {

             @BeforeMethod
 public void startMailServer() throws Exception{
               app.mail().start();
          }

      @Test
    public void testChangePassword() throws Exception {
           }


       @BeforeMethod(alwaysRun = true)
    public void stopMailServer() throws Exception {
               app.mail().stop();
           }
}
