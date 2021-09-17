package org.acm.store.model;

import javax.persistence.*;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

@Entity(name = "item")
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID")
    private long ID;
    @Column(name = "CartID")
    private long cartID;
    @Column(name = "ProductID")
    private long productID;
    @Column(name = "Count")
    private int count;

    public Item() {}

    public Item(long cartID, long productID, int count) {
        this.cartID = cartID;
        this.productID = productID;
        this.count = count;
    }

    public long getCartID() {
        return cartID;
    }

    public long getProductID() {
        return productID;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
