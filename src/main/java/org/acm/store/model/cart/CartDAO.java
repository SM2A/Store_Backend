package org.acm.store.model.cart;

import java.util.List;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

public interface CartDAO {

    public List<Cart> getAllCart();

    public List<Cart> getCart(long userID);

    public List<Cart> getCart(long userID,Status status);

    public void addCart(Cart cart);

    public void updateCart(Cart cart);
}
