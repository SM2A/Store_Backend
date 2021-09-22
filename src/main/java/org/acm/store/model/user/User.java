package org.acm.store.model.user;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID",unique = true,nullable = false)
    protected long ID;
    @Column(name = "FirstName",nullable = false)
    protected String firstName;
    @Column(name = "LastName",nullable = false)
    protected String lastName;
    @Column(name = "pass",nullable = false)
    protected String password;
    @Column(name = "Email",nullable = false)
    protected String email;
    @Column(name = "PhoneNumber",nullable = false,length = 11)
    protected String phoneNumber;
    @Column(name = "Address",nullable = false)
    protected String address;
    @Column(name = "RegisterDate",nullable = false)
    protected Date registerDate;

    public User() {}

    public User(String firstName, String lastName, String password, String email,
                String phoneNumber, String address) {
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
