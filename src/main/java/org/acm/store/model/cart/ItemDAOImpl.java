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
public class ItemDAOImpl implements ItemDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Item> getAllItems() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Item> list = session.createQuery("FROM item", Item.class).list();
        session.close();
        return list;
    }

    @Override
    public Item getItem(long ID) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Item item = session.get(Item.class, ID);
        session.close();
        return item;
    }

    @Override
    public Item getItem(long cartID, long productID) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Item item = session.createQuery(Item.GET_ITEM, Item.class)
                .setParameter("cid", cartID).setParameter("pid", productID).uniqueResult();
        session.close();
        return item;
    }

    @Override
    public List<Item> getCartItems(long cartID) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Item> list = session.createNamedQuery(Item.GET_CART_ITEMS, Item.class)
                .setParameter("cid", cartID).list();
        session.close();
        return list;
    }

    @Override
    public long getCartPrice(long cartID) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        long price = (long) session.createNamedQuery(Item.GET_CART_PRICE)
                .setParameter("cid", cartID).uniqueResult();
        session.close();
        return price;
    }

    @Override
    public void addItem(Item item) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(item);
        session.close();
        session.getTransaction().commit();
    }

    @Override
    public void updateItem(Item item) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(item);
        session.close();
        session.getTransaction().commit();
    }

    @Override
    public void deleteItem(long cartID, long productID) {
        Item item = getItem(cartID, productID);
        if (item != null) {
            Session session = this.sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.delete(item);
            session.close();
            session.getTransaction().commit();
        }
    }
}
