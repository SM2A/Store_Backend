package org.acm.store.model.comment;

import java.util.List;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

public interface CommentDAO {

    List<Comment> getAllComments();

    List<Comment> getProductComments(long productID);

    Comment getComment(long id);

    void addComment(Comment comment);

    void updateComment(Comment comment);

    void deleteComment(long id);
}
