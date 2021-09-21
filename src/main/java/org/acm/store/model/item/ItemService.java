package org.acm.store.model.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

@Service
public class ItemService {

    @Autowired
    ItemDAO itemDAO;

    @Transactional
    public List<Item> getAllItems() {
        return itemDAO.getAllItems();
    }

    @Transactional
    public Item getItem(long ID) {
        return itemDAO.getItem(ID);
    }

    @Transactional
    public Item getItem(long cartID, long productID) {
        return itemDAO.getItem(cartID, productID);
    }

    @Transactional
    public List<Item> getCartItems(long cartID) {
        return itemDAO.getCartItems(cartID);
    }

    @Transactional
    public long getCartPrice(long cartID) {
        return itemDAO.getCartPrice(cartID);
    }

    @Transactional
    public void addItem(Item item) {
        itemDAO.addItem(item);
    }

    @Transactional
    public void updateItem(Item item) {
        itemDAO.updateItem(item);
    }

    @Transactional
    public void deleteItem(long cartID, long productID) {
        itemDAO.deleteItem(cartID, productID);
    }
}
