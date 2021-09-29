package org.acm.store.model.cart;

import org.acm.store.model.user.customer.Costumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

@Service
public class CartService {

    @Autowired
    CartDAO cartDAO;

    @Transactional
    public List<Cart> getAllCart() {
        return cartDAO.getAllCart();
    }

    @Transactional
    public Cart getCart(long ID) {
        return cartDAO.getCart(ID);
    }

    @Transactional
    public List<Cart> getCart(long userID, Status status) {
        return cartDAO.getCart(userID, status);
    }

    @Transactional
    public void addCart(Cart cart) {
        cartDAO.addCart(cart);
    }

    @Transactional
    public void updateCart(Cart cart) {
        cartDAO.updateCart(cart);
    }
}
