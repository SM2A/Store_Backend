package org.acm.store.model.user.admin;

import org.acm.store.model.user.User;

import javax.persistence.*;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

@Entity(name = "admin")
@Table(name = "admin", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID"),
        @UniqueConstraint(columnNames = "Email"),
        @UniqueConstraint(columnNames = "PhoneNumber"),
})
@NamedQueries({
        @NamedQuery(name = Admin.GET_ADMIN_BY_EMAIL_PASSWORD, query = Admin.GET_ADMIN_BY_EMAIL_PASSWORD_Q),
        @NamedQuery(name = Admin.GET_ADMIN_BY_EMAIL_PHONENUMBER, query = Admin.GET_ADMIN_BY_EMAIL_PHONENUMBER_Q),
        @NamedQuery(name = Admin.GET_ADMIN_EDIT, query = Admin.GET_ADMIN_EDIT_Q),
})
public class Admin extends User {

    public static final String GET_ADMIN_BY_EMAIL_PASSWORD = "GET_ADMIN_ID_BY_EMAIL_PASSWORD";
    public static final String GET_ADMIN_BY_EMAIL_PASSWORD_Q
            = "FROM admin a WHERE a.email = :email AND a.password = :password";

    public static final String GET_ADMIN_BY_EMAIL_PHONENUMBER = "GET_ADMIN_BY_EMAIL_PHONENUMBER";
    public static final String GET_ADMIN_BY_EMAIL_PHONENUMBER_Q
            = "FROM admin a WHERE a.email = :email OR a.phoneNumber = :phonenumber";

    public static final String GET_ADMIN_EDIT = "GET_ADMIN_EDIT";
    public static final String GET_ADMIN_EDIT_Q
            = "FROM admin a WHERE a.id != :id AND (a.email = :email OR a.phoneNumber = :phonenumber)";

    public Admin() {}

    public Admin(String firstName, String lastName, String password, String email,
                 String phoneNumber, String address) {
        super(firstName, lastName, password, email, phoneNumber, address);
    }
}
