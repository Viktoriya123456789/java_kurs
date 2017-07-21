package ru.stga.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stga.pft.addressbook.model.ContactData;
import ru.stga.pft.addressbook.model.Contacts;
import ru.stga.pft.addressbook.model.GroupData;
import ru.stga.pft.addressbook.model.Groups;

import java.sql.*;

/**
 * Created by admin on 20.07.2017.
 */
public class DbConnectionTest {

    @Test(enabled = false)
    public void testDbConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=&serverTimezone=UTC");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select group_id,group_name,group_header,group_footer from group_list");
            Groups groups = new Groups();
            while (rs.next())
            {
                groups.add(new GroupData().withId(rs.getInt("group_id"))
                        .withName(rs.getString("group_name"))
                        .withHeader(rs.getString("group_header"))
                        .withFooter(rs.getString("group_footer")));
            }
            rs.close();
            st.close();
            conn.close();

            System.out.println(groups);

            // Do something with the Connection

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }



    @Test
    public void testDbConnectionContact() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=&serverTimezone=UTC");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select id,lastname,firstname,email,email2,email3,work,home,mobile," +
                    "address from addressbook where deprecated = '0000-00-00'");
            Contacts contacts = new Contacts();
            while (rs.next())
            {
                contacts.add(new ContactData().withId(rs.getInt("id"))
                        .withLastName(rs.getString("lastname"))
                        .withFirstName(rs.getString("firstname"))
                        .withAddress(rs.getString("address"))
                        .withEmail1(rs.getString("email"))
                        .withEmail2(rs.getString("email2"))
                        .withEmail3(rs.getString("email3"))
                        .withWorkPhone(rs.getString("work"))
                        .withMobilePhone(rs.getString("mobile"))
                        .withHomePhone(rs.getString("home")));

            }
            rs.close();
            st.close();
            conn.close();

            System.out.println(contacts);

            // Do something with the Connection

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
