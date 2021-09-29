package org.acm.store.model.user.customer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Costumer> getAllCustomers() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Costumer> list = session.createQuery("FROM costumer", Costumer.class).list();
        session.close();
        return list;
    }

    @Override
    public Costumer getCustomer(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Costumer costumer = session.get(Costumer.class, id);
        session.close();
        return costumer;
    }

    @Override
    public Costumer getCustomerEmailPassword(String email, String password) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Costumer costumer = session.createNamedQuery(Costumer.GET_CUSTOMER_BY_EMAIL_PASSWORD, Costumer.class)
                .setParameter("email", email).setParameter("password", password).uniqueResult();
        session.close();
        return costumer;
    }

    @Override
    public Costumer getCustomerEmailPhoneNumber(String email, String phoneNumber) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Costumer costumer = session.createNamedQuery(Costumer.GET_CUSTOMER_BY_EMAIL_PHONENUMBER, Costumer.class)
                .setParameter("email", email).setParameter("phonenumber", phoneNumber).uniqueResult();
        session.close();
        return costumer;
    }

    @Override
    public Costumer getCustomerEmailPhoneNumber(long id, String email, String phoneNumber) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Costumer costumer = session.createNamedQuery(Costumer.GET_CUSTOMER_EDIT, Costumer.class)
                .setParameter("email", email).setParameter("phonenumber", phoneNumber)
                .setParameter("id", id).uniqueResult();
        session.close();
        return costumer;
    }

    @Override
    public long addCustomer(Costumer costumer) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(costumer);
        session.getTransaction().commit();
        session.close();
        return costumer.getID();
    }

    @Override
    public void updateCustomer(Costumer costumer) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(costumer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteCustomer(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Costumer costumer = session.get(Costumer.class, id);
        if (costumer != null) session.delete(costumer);
        session.getTransaction().commit();
        session.close();
    }
}
