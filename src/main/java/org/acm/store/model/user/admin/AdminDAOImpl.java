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
        return session.createQuery("FROM admin ", Admin.class).list();
    }

    @Override
    public Admin getAdmin(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(Admin.class, id);
    }

    @Override
    public Admin getAdminEmailPassword(String email, String password) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createNamedQuery(Admin.GET_ADMIN_BY_EMAIL_PASSWORD, Admin.class)
                .setParameter("email", email).setParameter("password", password).uniqueResult();
    }

    @Override
    public Admin getAdminEmailPhoneNumber(String email, String phoneNumber) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createNamedQuery(Admin.GET_ADMIN_BY_EMAIL_PHONENUMBER, Admin.class)
                .setParameter("email", email).setParameter("phonenumber", phoneNumber).uniqueResult();
    }

    @Override
    public long addAdmin(Admin admin) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(admin);
        return admin.getID();
    }

    @Override
    public void updateAdmin(Admin admin) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(admin);
    }

    @Override
    public void deleteAdmin(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Admin admin = session.get(Admin.class, id);
        if (admin != null) session.delete(admin);
    }
}
