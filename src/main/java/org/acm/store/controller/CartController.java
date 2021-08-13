package org.acm.store.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.acm.store.model.Cart;
import org.acm.store.model.DataBase;
import org.acm.store.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;


@RestController
@RequestMapping("/carts")

public class CartController {

    @GetMapping
    public ArrayList<Cart> showUserCarts(@RequestBody ObjectNode json){
        User user = DataBase.getInstance().validateUser(json.get("email").asText(), json.get("password").asText());
        return DataBase.getInstance().showUserCarts(user.getID());
    }

    @GetMapping("/items")
    public HashMap<Long, Integer> showItemsInCurrentCart(@RequestBody ObjectNode json){
        User user = DataBase.getInstance().validateUser(json.get("email").asText(), json.get("password").asText());
        return DataBase.getInstance().getItemsInOpenCart(user.getID());
    }

    @PostMapping("/items/add")
    public void addItemToCart(@RequestBody ObjectNode json){
        User user = DataBase.getInstance().validateUser(json.get("email").asText(), json.get("password").asText());
        Cart cart = DataBase.getInstance().findOpenCartByUser(user.getID());
        if(cart == null){
            DataBase.getInstance().createCart(user.getID());
            cart = DataBase.getInstance().findOpenCartByUser(user.getID());
        }
        DataBase.getInstance().addItem(cart.getID(), json.get("productId").asLong());
    }

    @PostMapping("/items/subtract")
    public void omitItemFromCart(@RequestBody ObjectNode json){
        User user = DataBase.getInstance().validateUser(json.get("email").asText(), json.get("password").asText());
        Cart cart = DataBase.getInstance().findOpenCartByUser(user.getID());
        if(cart == null){
            //exception handling
        }
        DataBase.getInstance().decreaseItem(json.get("productId").asLong(), cart.getID());
    }

    @PostMapping("/items/delete")
    public void deleteItemFromCart(@RequestBody ObjectNode json){
        User user = DataBase.getInstance().validateUser(json.get("email").asText(), json.get("password").asText());
        Cart cart = DataBase.getInstance().findOpenCartByUser(user.getID());
        if(cart == null){
            //exception handling
        }
        DataBase.getInstance().deleteItem (json.get("productId").asLong(), cart.getID());
    }

    @PostMapping("/items/setq")
    public void setItemQuantityInCart(@RequestBody ObjectNode json){
        User user = DataBase.getInstance().validateUser(json.get("email").asText(), json.get("password").asText());
        Cart cart = DataBase.getInstance().findOpenCartByUser(user.getID());
        if(cart == null){
            DataBase.getInstance().createCart(user.getID());
            cart = DataBase.getInstance().findOpenCartByUser(user.getID());
        }
        DataBase.getInstance().setQuantityToAnItem(json.get("productId").asLong(), cart.getID(), json.get("quantity").asInt());
    }

}
