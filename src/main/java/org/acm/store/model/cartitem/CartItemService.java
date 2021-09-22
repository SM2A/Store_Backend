package org.acm.store.model.cartitem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

@Service
public class CartItemService {

    @Autowired
    CartItemDAO cartItemDAO;

    @Transactional
    public List<CartItem> getAllItems() {
        return cartItemDAO.getAllItems();
    }

    @Transactional
    public CartItem getItem(long ID) {
        return cartItemDAO.getItem(ID);
    }

    @Transactional
    public CartItem getItem(long cartID, long productID) {
        return cartItemDAO.getItem(cartID, productID);
    }

    @Transactional
    public List<CartItem> getCartItems(long cartID) {
        return cartItemDAO.getCartItems(cartID);
    }

    @Transactional
    public long getCartPrice(long cartID) {
        return cartItemDAO.getCartPrice(cartID);
    }

    @Transactional
    public void addItem(CartItem item) {
        cartItemDAO.addItem(item);
    }

    @Transactional
    public void updateItem(CartItem item) {
        cartItemDAO.updateItem(item);
    }

    @Transactional
    public void deleteItem(long cartID, long productID) {
        cartItemDAO.deleteItem(cartID, productID);
    }
}
