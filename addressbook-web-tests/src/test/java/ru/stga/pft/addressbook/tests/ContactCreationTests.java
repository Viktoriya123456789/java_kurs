package ru.stga.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;
import ru.stga.pft.addressbook.model.Contacts;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        //list.add(new Object[] {new ContactData().withFirstName("firstname 1").withLastName("lastname 1").withAddress("address 1")
                //.withWorkPhone("workPhone 1").withHomePhone("homePhone 1").withMobilePhone("mobilePhone 1")
               // .withEmail1("email1 1").withEmail2("email2 1").withEmail3("email3 1")});
       // list.add(new Object[] {new ContactData().withFirstName("firstname 2").withLastName("lastname 2").withAddress("address 2")
               // .withWorkPhone("workPhone 2").withHomePhone("homePhone 2").withMobilePhone("mobilePhone 2")
                //.withEmail1("email1 2").withEmail2("email2 2").withEmail3("email3 2")});
       // list.add(new Object[] {new ContactData().withFirstName("firstname 3").withLastName("lastname 3").withAddress("address 3")
                //.withWorkPhone("workPhone 3").withHomePhone("homePhone 3").withMobilePhone("mobilePhone 3")
                //.withEmail1("email1 3").withEmail2("email2 3").withEmail3("email3 3")});
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.csv"));
        String line = reader.readLine();
        while(line !=null){
            String [] split = line.split(";");
            list.add(new Object[] {new ContactData().withFirstName(split[0]).withLastName(split[1])});
                    //.withAddress(split[2])
                       // .withWorkPhone(split[9]).withHomePhone(split[4]).withMobilePhone(split[5])
                        //.withEmail1(split[6]).withEmail2(split[7]).withEmail3(split[8])});
            line = reader.readLine();
        }
        return list.iterator();
    }


    @Test(dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) {
        app.goTo().goToPage();
        Contacts before = app.contact().all();
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size() +1));
        Contacts after = app.contact().all();
        //assertThat(app.contact().count(), equalTo(before.size() +1));
        //assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test(enabled = false)
    public void testBadContactCreation() {
        app.goTo().goToPage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstName("test'").withLastName("test1'").withAddress("test2'");
        app.contact().create(contact);
        assertThat(app.group().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }

}





