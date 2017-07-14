package ru.stga.pft.addressbook.tests;

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

        assertThat (contact.getAddress(),equalTo(mergeAddress(contactInfoFromEditForm)));
    }

    private String mergeAddress(ContactData contact) {
        return Arrays.asList(contact.getAddress())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactAddressTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned (String address){
        return address.replaceAll("\\s", "").replaceAll ("-()","");
    }

}

