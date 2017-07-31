package ru.stga.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stga.pft.mantis.model.MailMessage;

import java.util.List;

import static org.testng.Assert.assertTrue;


public class ResetPassword extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testResetPassword() throws Exception {

        app.goTo().login("administrator", "root");
        //app.goTo().manage(); //нажать на Manage - не работает
        app.goTo().usersManage(); //нажать на Manage Users - переходит

        String user = app.db().getUserName(); //Выбор одного пользователя из базы -  работает
        app.resetPassword().select(user);

        app.resetPassword().resert(); // нажать на Reset Password - работает
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 100000);
        String email = user + "@localhost";
        String confirmationLink = findConfirmationLink(mailMessages, email); // читает ссылку для смены пароля - работает

        String newPassword = String.valueOf(System.currentTimeMillis());
        app.resetPassword().finish(confirmationLink, newPassword);
        assertTrue(app.newSession().login(user, newPassword));



    }

    protected String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }


    @AfterMethod
    public void stopMailServer() {
        app.mail().stop();
    }
}