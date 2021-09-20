package org.acm.store.model.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

@Service
public class CommentService {

    @Autowired
    CommentDAO commentDAO;

    @Transactional
    public List<Comment> getAllComments() {
        return commentDAO.getAllComments();
    }

    @Transactional
    public List<Comment> getProductComments(long productID) {
        return commentDAO.getProductComments(productID);
    }

    @Transactional
    public Comment getComment(long id) {
        return commentDAO.getComment(id);
    }

    @Transactional
    public void addComment(Comment comment) {
        commentDAO.addComment(comment);
    }

    @Transactional
    public void updateComment(Comment comment) {
        commentDAO.updateComment(comment);
    }

    @Transactional
    public void deleteComment(long id) {
        commentDAO.deleteComment(id);
    }
}
