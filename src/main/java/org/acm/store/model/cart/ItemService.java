package org.acm.store.model.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

@Service
public class ItemService {

    @Autowired
    ItemDAO itemDAO;
}
