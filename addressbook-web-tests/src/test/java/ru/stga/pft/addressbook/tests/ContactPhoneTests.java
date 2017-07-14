package ru.stga.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by admin on 13.07.2017.
 */
public class ContactPhoneTests extends TestBase {

    @Test
    public void testContactPhones (){
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat (contact.getHomePhone(),equalTo (contactInfoFromEditForm.getHomePhone()));
        assertThat (contact.getMobilePhone(),equalTo (contactInfoFromEditForm.getMobilePhone()));
        assertThat (contact.getWorkPhone(),equalTo (contactInfoFromEditForm.getWorkPhone()));
    }
        public String cleaned (String phone){
            return phone.replaceAll("\\s", "").replaceAll ("-()","");
    }

}
