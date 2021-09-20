package org.acm.store.model.user.customer;

import java.util.List;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

public interface CustomerDAO {

    public List<Costumer> getAllCustomers();

    public Costumer getCustomer(long id);

    public long addCustomer(Costumer costumer);

    public void updateCustomer(Costumer costumer);

    public void deleteCustomer(long id);
}
