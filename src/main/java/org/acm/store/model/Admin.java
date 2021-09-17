package org.acm.store.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

@Entity(name = "admin")
@Table(name = "admin")
public class Admin extends User{

    public Admin() {}

    public Admin(String firstName, String lastName, String password, String email,
                 String phoneNumber, String address) {
        super(firstName, lastName, password, email, phoneNumber, address);
    }
    //other permissions...
}
