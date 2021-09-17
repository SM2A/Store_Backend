package org.acm.store.model;

import javax.persistence.*;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

@Entity(name = "item")
@Table(name = "item")
@NamedQueries({
        @NamedQuery(name = "GET_CART_PRICE", query = Item.GET_CART_PRICE),
        @NamedQuery(name = "GET_CART_ITEMS", query = Item.GET_CART_ITEMS),
        @NamedQuery(name = "GET_ITEM", query = Item.GET_ITEM),
})
public class Item {

    public static final String GET_CART_PRICE
            = "SELECT SUM(item.count * product.price)" +
            "FROM item i INNER JOIN product ON i.productID=product.id WHERE i.cartID = :cid";

    public static final String GET_CART_ITEMS
            = "SELECT product FROM item i INNER JOIN product ON i.productID=product.id WHERE i.cartID = :cid";

    public static final String GET_ITEM
            = "FROM item i WHERE i.cartID = :cid AND i.productID = :pid";

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
