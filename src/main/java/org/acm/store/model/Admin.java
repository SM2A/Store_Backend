package org.acm.store.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

@Entity(name = "admin")
@Table(name = "admin")
@NamedQueries({
        @NamedQuery(name = "GET_ADMIN_ID_BY_EMAIL_PASSWORD", query = Admin.GET_ADMIN_ID_BY_EMAIL_PASSWORD)
})
public class Admin extends User{

    public static final String GET_ADMIN_ID_BY_EMAIL_PASSWORD
            = "FROM admin a WHERE a.email = :email AND a.password = :password";

    public Admin() {}

    public Admin(String firstName, String lastName, String password, String email,
                 String phoneNumber, String address) {
        super(firstName, lastName, password, email, phoneNumber, address);
    }
    //other permissions...
}
