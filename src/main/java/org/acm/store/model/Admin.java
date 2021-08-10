package org.acm.store.model;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

public class Admin extends User{
    public Admin(long ID, String firstName, String lastName, String password, String email,
                 String phoneNumber, String address) {
        super(ID, firstName, lastName, password, email, phoneNumber, address);
    }
    //other permissions...
}
