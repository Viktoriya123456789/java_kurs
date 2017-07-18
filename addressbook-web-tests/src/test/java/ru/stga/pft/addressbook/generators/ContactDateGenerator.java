package ru.stga.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stga.pft.addressbook.model.ContactData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 18.07.2017.
 */
public class ContactDateGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    public static void main (String[] args) throws IOException {
        ContactDateGenerator generator = new ContactDateGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        }   catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        save(contacts, new File (file));
    }


    private void save(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts){
            //writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(),
                   // contact.getAddress(), contact.getEmail1(), contact.getEmail2(), contact.getEmail3(),
                    //contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()));
                    //contact.getAllPhones(),contact.getAllEmailes()));

        writer.write(String.format("%s;%s\n", contact.getFirstname(), contact.getLastname()));
               // contact.getAddress(), contact.getEmail1(), contact.getEmail2(), contact.getEmail3(),
               // contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()));
        //contact.getAllPhones(),contact.getAllEmailes()));
    }
        writer.close();
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstName(String.format("firstname %s", i))
                    .withLastName(String.format("lastname %s", i)));
                    //.withAddress(String.format("address %s", i))
                    //.withWorkPhone(String.format("work %s", i))
                    //.withHomePhone(String.format("home %s", i))
                   // .withMobilePhone(String.format("mobile %s", i))
                    //.withEmail1(String.format("email %s", i))
                    //.withEmail2(String.format("email2 %s", i))
                   // .withEmail3(String.format("email3 %s", i)));

                    //.withAllPhones(String.format("allPhones %s", i))
                    //.withAllEmailes(String.format("allEmailes %s", i)));
        }
        return contacts;
    }

}



