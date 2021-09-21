package org.acm.store.model.item;

import javax.persistence.*;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

@Entity(name = "item")
@Table(name = "item",uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID")
})
@NamedQueries({
        @NamedQuery(name = Item.GET_CART_PRICE, query = Item.GET_CART_PRICE_Q),
        @NamedQuery(name = Item.GET_CART_ITEMS, query = Item.GET_CART_ITEMS_Q),
        @NamedQuery(name = Item.GET_ITEM, query = Item.GET_ITEM_Q),
})
public class Item {

    public static final String GET_CART_PRICE = "GET_CART_PRICE";
    public static final String GET_CART_PRICE_Q
            = "SELECT SUM(item.count * product.price)" +
            "FROM item i INNER JOIN product ON i.productID=product.id WHERE i.cartID = :cid";

    public static final String GET_CART_ITEMS = "GET_CART_ITEMS";
    public static final String GET_CART_ITEMS_Q
            = "FROM item i WHERE i.cartID = :cid";

    public static final String GET_ITEM = "GET_ITEM";
    public static final String GET_ITEM_Q
            = "FROM item i WHERE i.cartID = :cid AND i.productID = :pid";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
