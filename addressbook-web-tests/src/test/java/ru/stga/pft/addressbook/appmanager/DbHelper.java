package ru.stga.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stga.pft.addressbook.model.ContactData;
import ru.stga.pft.addressbook.model.Contacts;
import ru.stga.pft.addressbook.model.GroupData;
import ru.stga.pft.addressbook.model.Groups;

import java.util.List;

/**
 * Created by admin on 21.07.2017.
 */
public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list();

        session.getTransaction().commit();
        session.close();
        return new Groups(result);

    }
    public GroupData groupById(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        GroupData result = (GroupData) session.createQuery("from GroupData where id = :id").setParameter("id",id).getSingleResult();

        session.getTransaction().commit();
        session.close();
        return result;

    }

    public Contacts contacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);




    }

    public Contacts contactNotInGroup() {
        Contacts result = new Contacts();
        Groups groupsFull = groups();
        Contacts contactsFull = contacts();
        for (ContactData contact : contactsFull) {
            if (contact.getGroups().size() < groupsFull.size()) {
                result.add(contact);
            }
        }
        return new Contacts(result);
    }

    public Contacts contactInGroup() {
        Contacts result = new Contacts();
        Groups groupsFull = groups();
        Contacts contactsFull = contacts();
        for (ContactData contact : contactsFull) {
            if (contact.getGroups().size() < groupsFull.size()) {
                result.add(contact);
            }
        }
        return new Contacts(result);
    }

    public GroupData getNewGroup() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        GroupData result = (GroupData) session.createQuery("from GroupData g where g.id = (select max(id) from GroupData)").getSingleResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public ContactData getNewContact() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ContactData result = (ContactData) session.createQuery("from ContactData g where g.id = (select max(id) from ContactData)").getSingleResult();
        session.getTransaction().commit();
        session.close();
        return result;

    }
}
