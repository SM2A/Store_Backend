package org.acm.store.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "cart", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID")
})
@Entity(name = "cart")
@NamedQueries({
        @NamedQuery(name = "GET_USER_OPEN_CART_BY_ID", query = Cart.GET_USER_OPEN_CART)
})
public class Cart {

    public static final String GET_USER_OPEN_CART
            = "FROM cart c WHERE c.userID = :id AND c.status = :status";

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    private long ID;
    @Column(name = "Status",columnDefinition = "enum('OPEN', 'CLOSE')")
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "UserID")
    private long userID;
    @Column(name = "PurchaseDate")
    private Date purchaseDate;

    public Cart() {}

    public Cart(long userID) {
        this.status = Status.OPEN;
        this.userID = userID;
    }

    public long getID() {
        return ID;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Status getStatus() {
        return status;
    }

    public long getUserID() {
        return userID;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void purchase() {
        purchaseDate = new Date();
        purchaseDate.getTime();
    }
}
