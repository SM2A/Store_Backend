package model;

import java.util.Date;

public class User {

    protected String userID;
    protected String firstName;
    protected String lastName;
    protected String password;
    protected String email;
    protected String phoneNumber;
    protected String address;
    protected Date registerDate;

    public User(String userID, String firstName, String lastName, String password, String email,
                String phoneNumber, String address) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        registerDate = new Date();
        registerDate.getTime();
    }

    public String getUserID() {
        return userID;
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

    public boolean verify(String enteredEmail, String enteredPassword) {
        return email.equals(enteredEmail) && password.equals(enteredPassword);
    }

}

class Costumer extends User {
    private float credit;

    public Costumer(String userID, String firstName, String lastName, String password,
                    String email, String phoneNumber, String address) {
        super(userID, firstName, lastName, password, email, phoneNumber, address);
        credit = 0;
    }

    public float getCredit() {
        return credit;
    }

    public void addCredit(float amount) {
        credit += amount;
    }

    public void purchase(float totalPrice) {
        if (credit < totalPrice)
            System.out.println("Insufficient Funds!");
        else
            credit -= totalPrice;
    }
}

class Admin extends User {
    public Admin(String userID, String firstName, String lastName, String password, String email,
                 String phoneNumber, String address) {
        super(userID, firstName, lastName, password, email, phoneNumber, address);
    }
    //other permissions...
}
