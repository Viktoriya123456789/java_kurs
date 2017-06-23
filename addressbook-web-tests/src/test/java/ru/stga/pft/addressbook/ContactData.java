package ru.stga.pft.addressbook;

public class ContactData {
    private final String firstname;
    private final String lastname;
    private final String address;
    private final String telephone;
    private final String email;

    public ContactData(String firstname, String lastname, String address, String telephone, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }
}
