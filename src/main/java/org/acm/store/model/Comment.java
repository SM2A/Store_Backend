package org.acm.store.model;

import javax.persistence.*;

@Table(name = "comment", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID")
})
@Entity(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID")
    private long ID;
    @Column(name = "UserId")
    private long userID;
    @Column(name = "ProductID")
    private long productID;
    @Column(name = "Content")
    private String text;
    @Column(name = "Like")
    private int likes;
    @Column(name = "Dislike")
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
