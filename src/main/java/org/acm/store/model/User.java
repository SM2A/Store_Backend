package org.acm.store.model;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
    @Column(name = "ID")
    protected long ID;
    @Column(name = "FirstName")
    protected String firstName;
    @Column(name = "LastName")
    protected String lastName;
    @Column(name = "Password")
    protected String password;
    @Column(name = "Email")
    protected String email;
    @Column(name = "PhoneNumber")
    protected String phoneNumber;
    @Column(name = "Address")
    protected String address;
    @Column(name = "RegisterDate")
    protected Date registerDate;

    public User() {}

    public User(String firstName, String lastName, String password, String email,
                String phoneNumber, String address) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        registerDate = new Date();
        registerDate.getTime();
    }

    public long getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

}
