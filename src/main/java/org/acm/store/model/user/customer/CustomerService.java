package org.acm.store.model.user.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

@Service("customerService")
public class CustomerService {

    @Autowired
    CustomerDAO customerDAO;

    @Transactional
    public List<Costumer> getAllCustomers(){
        return customerDAO.getAllCustomers();
    }

    @Transactional
    public Costumer getCustomer(long id){
        return customerDAO.getCustomer(id);
    }

    @Transactional
    public long addCustomer(Costumer customer){
        return customerDAO.addCustomer(customer);
    }

    @Transactional
    public void updateCustomer(Costumer customer){
        customerDAO.updateCustomer(customer);
    }

    @Transactional
    public void deleteCustomer(long id){
        customerDAO.deleteCustomer(id);
    }
}
