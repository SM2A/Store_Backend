package org.acm.store.model.cart;

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
public class CartDAOImpl implements CartDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Cart> getAllCart() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Cart> list = session.createQuery("FROM cart", Cart.class).list();
        session.close();
        return list;
    }

    @Override
    public Cart getCart(long ID) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Cart cart = session.get(Cart.class, ID);
        session.close();
        return cart;
    }

    @Override
    public List<Cart> getCart(long userID, Status status) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Cart> list = session.createNamedQuery(Cart.GET_USER_CARTS_STATUS, Cart.class)
                .setParameter("id", userID).setParameter("status", status.toString()).list();
        session.close();
        return list;
    }

    @Override
    public void addCart(Cart cart) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(cart);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateCart(Cart cart) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(cart);
        session.getTransaction().commit();
        session.close();
    }
}
