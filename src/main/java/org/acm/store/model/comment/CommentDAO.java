package org.acm.store.model.comment;

import java.util.List;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

public interface CommentDAO {

    public List<Comment> getAllComments();

    public List<Comment> getProductComments(long productID);

    public Comment getComment(long id);

    public void addComment(Comment comment);

    public void updateComment(Comment comment);

    public void deleteComment(long id);
}
