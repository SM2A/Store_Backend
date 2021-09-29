package org.acm.store.model.cart;

import java.util.List;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

public interface CartDAO {

    List<Cart> getAllCart();

    Cart getCart(long ID);

    List<Cart> getCart(long userID, Status status);

    void addCart(Cart cart);

    void updateCart(Cart cart);
}
