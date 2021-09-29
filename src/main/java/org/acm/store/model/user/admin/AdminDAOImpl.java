package org.acm.store.model.user.admin;

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
public class AdminDAOImpl implements AdminDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Admin> getAllAdmins() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Admin> list = session.createQuery("FROM admin ", Admin.class).list();
        session.close();
        return list;
    }

    @Override
    public Admin getAdmin(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Admin admin = session.get(Admin.class, id);
        session.close();
        return admin;
    }

    @Override
    public Admin getAdminEmailPassword(String email, String password) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Admin admin = session.createNamedQuery(Admin.GET_ADMIN_BY_EMAIL_PASSWORD, Admin.class)
                .setParameter("email", email).setParameter("password", password).uniqueResult();
        session.close();
        return admin;
    }

    @Override
    public Admin getAdminEmailPhoneNumber(String email, String phoneNumber) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Admin admin = session.createNamedQuery(Admin.GET_ADMIN_BY_EMAIL_PHONENUMBER, Admin.class)
                .setParameter("email", email).setParameter("phonenumber", phoneNumber).uniqueResult();
        session.close();
        return admin;
    }

    @Override
    public Admin getAdminEmailPhoneNumber(long id, String email, String phoneNumber) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Admin admin = session.createNamedQuery(Admin.GET_ADMIN_EDIT, Admin.class).setParameter("id", id)
                .setParameter("email", email).setParameter("phonenumber", phoneNumber).uniqueResult();
        session.close();
        return admin;
    }

    @Override
    public long addAdmin(Admin admin) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(admin);
        session.getTransaction().commit();
        return admin.getID();
    }

    @Override
    public void updateAdmin(Admin admin) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(admin);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteAdmin(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Admin admin = session.get(Admin.class, id);
        if (admin != null) session.delete(admin);
        session.getTransaction().commit();
        session.close();
    }
}
