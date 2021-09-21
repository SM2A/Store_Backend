package org.acm.store.model.item;

import java.util.List;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

public interface ItemDAO {

    List<Item> getAllItems();

    Item getItem(long ID);

    Item getItem(long cartID, long productID);

    List<Item> getCartItems(long cartID);

    long getCartPrice(long cartID);

    void addItem(Item item);

    void updateItem(Item item);

    void deleteItem(long cartID, long productID);
}
