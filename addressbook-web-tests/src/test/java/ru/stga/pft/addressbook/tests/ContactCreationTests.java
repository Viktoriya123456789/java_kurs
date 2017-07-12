package ru.stga.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;


public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() {
        app.goTo().goToPage();
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData().withFirstName("test").withLastName("test1").withAddress("test2");
        app.contact().create(contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}





