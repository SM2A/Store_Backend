package org.acm.store.model.user.customer;

import java.util.List;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

public interface CustomerDAO {

    List<Costumer> getAllCustomers();

    Costumer getCustomer(long id);

    Costumer getCustomerEmailPassword(String email, String password);

    Costumer getCustomerEmailPhoneNumber(String email, String phoneNumber);

    long addCustomer(Costumer costumer);

    void updateCustomer(Costumer costumer);

    void deleteCustomer(long id);
}
