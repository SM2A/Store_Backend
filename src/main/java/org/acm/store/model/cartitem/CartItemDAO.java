package org.acm.store.model.cartitem;

import java.util.List;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

public interface CartItemDAO {

    List<CartItem> getAllItems();

    CartItem getItem(long ID);

    CartItem getItem(long cartID, long productID);

    List<CartItem> getCartItems(long cartID);

    long getCartPrice(long cartID);

    void addItem(CartItem item);

    void updateItem(CartItem item);

    void deleteItem(long cartID, long productID);
}
