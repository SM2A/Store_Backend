package org.acm.store.model.cartitem;

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
public class CartItemDAOImpl implements CartItemDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<CartItem> getAllItems() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<CartItem> list = session.createQuery("FROM cart_item ", CartItem.class).list();
        session.close();
        return list;
    }

    @Override
    public CartItem getItem(long ID) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        CartItem item = session.get(CartItem.class, ID);
        session.close();
        return item;
    }

    @Override
    public CartItem getItem(long cartID, long productID) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        CartItem item = session.createNamedQuery(CartItem.GET_ITEM, CartItem.class)
                .setParameter("cid", cartID).setParameter("pid", productID).uniqueResult();
        session.close();
        return item;
    }

    @Override
    public List<CartItem> getCartItems(long cartID) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<CartItem> list = session.createNamedQuery(CartItem.GET_CART_ITEMS, CartItem.class)
                .setParameter("cid", cartID).list();
        session.close();
        return list;
    }

    @Override
    public long getCartPrice(long cartID) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Long price = (Long) session.createNamedQuery(CartItem.GET_CART_PRICE)
                .setParameter("cid", cartID).uniqueResult();
        session.close();
        if (price == null) price = 0L;
        return price;
    }

    @Override
    public void addItem(CartItem item) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateItem(CartItem item) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteItem(long cartID, long productID) {
        CartItem item = getItem(cartID, productID);
        if (item != null) {
            Session session = this.sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.delete(item);
            session.getTransaction().commit();
            session.close();
        }
    }
}
