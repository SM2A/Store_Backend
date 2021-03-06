package org.acm.store.model.product;

import org.acm.store.model.category.Category;
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
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Product> getAllProduct() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Product> list = session.createQuery("FROM product", Product.class).list();
        session.close();
        return list;
    }

    @Override
    public Product getProduct(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.close();
        return product;
    }

    @Override
    public List<Product> getProduct(String title) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Product> list = session.createNamedQuery(Product.GET_PRODUCT_BY_TITLE, Product.class)
                .setParameter("title", title).list();
        session.close();
        return list;
    }

    @Override
    public List<Product> getProduct(Category category) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Product> list = session.createNamedQuery(Product.GET_PRODUCT_BY_CATEGORY, Product.class)
                .setParameter("category", category.getName()).list();
        session.close();
        return list;
    }

    @Override
    public Product getProduct(String title, Category category) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Product product = session.createNamedQuery(Product.GET_PRODUCT_BY_TITLE_CATEGORY, Product.class)
                .setParameter("category", category.getName()).setParameter("title", title).uniqueResult();
        session.close();
        return product;
    }

    @Override
    public long addProduct(Product product) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();
        session.close();
        return product.getID();
    }

    @Override
    public void updateProduct(Product product) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(product);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteProduct(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        if (product != null) session.delete(product);
        session.getTransaction().commit();
        session.close();
    }
}
