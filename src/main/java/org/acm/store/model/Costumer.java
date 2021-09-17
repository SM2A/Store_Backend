package org.acm.store.model;

import org.acm.store.controller.validation.CustomException;

import javax.persistence.*;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

@Entity(name = "costumer")
@Table(name = "customer")
@NamedQueries({
        @NamedQuery(name = "GET_CUSTOMER_ID_BY_EMAIL_PASSWORD", query = Costumer.GET_CUSTOMER_ID_BY_EMAIL_PASSWORD),
        @NamedQuery(name = "GET_CUSTOMER_ID_BY_EMAIL_PHONENUMBER", query = Costumer.GET_CUSTOMER_ID_BY_EMAIL_PHONENUMBER)
})
public class Costumer extends User{

    public static final String GET_CUSTOMER_ID_BY_EMAIL_PASSWORD
            = "SELECT costumer FROM costumer c WHERE c.email = :email AND c.password = :password";

    public static final String GET_CUSTOMER_ID_BY_EMAIL_PHONENUMBER
            = "SELECT costumer FROM costumer c WHERE c.email = :email AND c.phoneNumber = :phonenumber";

    @Column(name = "Credit")
    private long credit;

    public Costumer() {}

    public Costumer(String firstName, String lastName, String password,
                    String email, String phoneNumber, String address) {
        super(firstName, lastName, password, email, phoneNumber, address);
        credit = 0;
    }

    public long getCredit() {
        return credit;
    }

    public void addCredit(long amount) {
        credit += amount;
    }

    public boolean hasEnoughCredit(long cost){
        return credit >= cost;
    }

    public void purchase(long totalPrice) {
        if (credit < totalPrice) {
            System.out.println("Insufficient Funds!");
            throw new CustomException("Insufficient Funds!");
        }
        else
            credit -= totalPrice;
    }
}
