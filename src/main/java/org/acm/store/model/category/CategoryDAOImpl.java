package org.acm.store.model.category;

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
public class CategoryDAOImpl implements CategoryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Category> getAllCategories() {
        return this.sessionFactory.getCurrentSession().createQuery("FROM category", Category.class).list();
    }

    @Override
    public Category getCategory(long id) {
        return this.sessionFactory.getCurrentSession().get(Category.class, id);
    }

    @Override
    public Category getCategory(String name) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Category category = session.createNamedQuery(Category.SEARCH_CATEGORY, Category.class)
                .setParameter("name", name).uniqueResult();
        session.close();
        return category;
    }

    @Override
    public void addCategory(Category category) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(category);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateCategory(Category category) {
        this.sessionFactory.getCurrentSession().update(category);
    }

    @Override
    public void deleteCategory(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Category category = session.get(Category.class, id);
        if (category != null) session.delete(category);
    }
}
