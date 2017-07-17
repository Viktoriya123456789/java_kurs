package ru.stga.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static ru.stga.pft.addressbook.tests.TestBase.app;

/**
 * Created by admin on 14.07.2017.
 */
public class ContactAddressTests extends TestBase {

    @Test
    public void testContactAddress (){
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat (contact.getAddress()
                ,equalTo(contactInfoFromEditForm.getAddress()));
    }


    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().gotoHomePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("Test")
                    .withLastName("Testtest")
                    .withEmail1("test1").withEmail2("test2").withEmail3("test3")
                    .withMobilePhone("123 445").withHomePhone("78").withWorkPhone("99 - 00")
                    .withAddress("test"));
        }
    }}

