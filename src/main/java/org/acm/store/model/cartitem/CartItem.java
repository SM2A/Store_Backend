package org.acm.store.model.cartitem;

import javax.persistence.*;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

@Table(name = "cart_item", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID")
})
@Entity(name = "cart_item")
@NamedQueries({
        @NamedQuery(name = CartItem.GET_CART_PRICE, query = CartItem.GET_CART_PRICE_Q),
        @NamedQuery(name = CartItem.GET_CART_ITEMS, query = CartItem.GET_CART_ITEMS_Q),
        @NamedQuery(name = CartItem.GET_ITEM, query = CartItem.GET_ITEM_Q),
})
public class CartItem {

    public static final String GET_CART_PRICE = "GET_CART_PRICE";
    public static final String GET_CART_PRICE_Q
            = "SELECT SUM(i.count * p.price)" +
            "FROM cart_item i INNER JOIN product p ON i.productID=p.id WHERE i.cartID = :cid";

    public static final String GET_CART_ITEMS = "GET_CART_ITEMS";
    public static final String GET_CART_ITEMS_Q
            = "FROM cart_item i WHERE i.cartID = :cid";

    public static final String GET_ITEM = "GET_ITEM";
    public static final String GET_ITEM_Q
            = "FROM cart_item i WHERE i.cartID = :cid AND i.productID = :pid";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID",nullable = false)
    private long ID;
    @Column(name = "CartID",nullable = false)
    private long cartID;
    @Column(name = "ProductID",nullable = false)
    private long productID;
    @Column(name = "item_count",nullable = false)
    private int count;

    public CartItem() {}

    public CartItem(long cartID, long productID, int count) {
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
