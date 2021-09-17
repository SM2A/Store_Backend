package org.acm.store.model;

import org.acm.store.controller.validation.CustomException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

@Entity(name = "costumer")
@Table(name = "customer")
public class Costumer extends User{

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
