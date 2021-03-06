package org.acm.store.model.comment;

import javax.persistence.*;

@Table(name = "comment", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID")
})
@Entity(name = "comment")
@NamedQueries({
        @NamedQuery(name = Comment.GET_COMMENT_BY_PRODUCTID, query = Comment.GET_COMMENT_BY_PRODUCTID_Q)
})
public class Comment {

    public static final String GET_COMMENT_BY_PRODUCTID = "GET_COMMENT_BY_PRODUCTID";
    public static final String GET_COMMENT_BY_PRODUCTID_Q = "FROM comment c WHERE c.productID = :pid";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private long ID;
    @Column(name = "UserId", nullable = false)
    private long userID;
    @Column(name = "ProductID", nullable = false)
    private long productID;
    @Column(name = "Content", nullable = false)
    private String text;
    @Column(name = "Likes", nullable = false)
    private int likes;
    @Column(name = "Dislikes", nullable = false)
    private int dislikes;

    public Comment() {}

    public Comment(long userID, long productID, String text) {
        this.userID = userID;
        this.productID = productID;
        this.text = text;
        likes = 0;
        dislikes = 0;
    }

    public long getID() {
        return ID;
    }

    public long getUserID() {
        return userID;
    }

    public long getProductID() {
        return productID;
    }

    public String getText() {
        return text;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void like() {
        likes++;
    }

    public void dislike() {
        dislikes++;
    }
}
