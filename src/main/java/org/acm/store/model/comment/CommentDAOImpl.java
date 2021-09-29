package org.acm.store.model.comment;

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
public class CommentDAOImpl implements CommentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Comment> getAllComments() {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Comment> list = session.createQuery("FROM comment", Comment.class).list();
        session.close();
        return list;
    }

    @Override
    public List<Comment> getProductComments(long productID) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Comment> list = session.createNamedQuery(Comment.GET_COMMENT_BY_PRODUCTID, Comment.class)
                .setParameter("pid", productID).list();
        session.close();
        return list;
    }

    @Override
    public Comment getComment(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Comment comment = session.get(Comment.class, id);
        session.close();
        return comment;
    }

    @Override
    public void addComment(Comment comment) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(comment);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateComment(Comment comment) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(comment);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteComment(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Comment comment = session.get(Comment.class, id);
        if (comment != null) session.delete(comment);
        session.getTransaction().commit();
        session.close();
    }
}
