package ru.stga.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by admin on 14.07.2017.
 */
public class ContactEmailTests extends TestBase {

    @Test
    public void testContactEmail (){
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat (contact.getAllEmailes(),equalTo(mergeEmail(contactInfoFromEditForm)));
    }

    private String mergeEmail(ContactData contact) {
        return Arrays.asList(contact.getEmail1(),contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
               // .map(ContactEmailTests::cleaned)
                .collect(Collectors.joining("\n"));
    }



}
