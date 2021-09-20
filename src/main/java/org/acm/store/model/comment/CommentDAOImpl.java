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
        return this.sessionFactory.getCurrentSession().createQuery("FROM comment", Comment.class).list();
    }

    @Override
    public List<Comment> getProductComments(long productID) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createNamedQuery(Comment.GET_COMMENT_BY_PRODUCTID, Comment.class)
                .setParameter("pid", productID).list();
    }

    @Override
    public Comment getComment(long id) {
        return this.sessionFactory.getCurrentSession().get(Comment.class, id);
    }

    @Override
    public void addComment(Comment comment) {
        this.sessionFactory.getCurrentSession().save(comment);
    }

    @Override
    public void updateComment(Comment comment) {
        this.sessionFactory.getCurrentSession().update(comment);
    }

    @Override
    public void deleteComment(long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Comment comment = session.get(Comment.class, id);
        if (comment != null) session.delete(comment);
    }
}
