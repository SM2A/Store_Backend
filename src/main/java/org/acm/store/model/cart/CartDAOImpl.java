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
        return this.sessionFactory.getCurrentSession().createQuery("FROM cart", Cart.class).list();
    }

    @Override
    public List<Cart> getCart(long userID) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createNamedQuery(Cart.GET_USER_CARTS, Cart.class)
                .setParameter("id", userID).list();
    }

    @Override
    public List<Cart> getCart(long userID, Status status) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createNamedQuery(Cart.GET_USER_CARTS, Cart.class)
                .setParameter("id", userID).setParameter("status", status).list();
    }

    @Override
    public void addCart(Cart cart) {
        this.sessionFactory.getCurrentSession().save(cart);
    }

    @Override
    public void updateCart(Cart cart) {
        this.sessionFactory.getCurrentSession().update(cart);
    }
}
