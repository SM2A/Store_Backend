package org.acm.store.model.cart;

import java.util.List;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

public interface CartDAO {

    public List<Cart> getAllCart();

    public Cart getCart(long ID);

    public List<Cart> getCart(long userID, Status status);

    public void addCart(Cart cart);

    public void updateCart(Cart cart);
}
