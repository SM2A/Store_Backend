package org.acm.store.controller;

import com.google.gson.reflect.TypeToken;
import org.acm.store.model.Cart;
import org.acm.store.model.DataBase;
import org.acm.store.model.User;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.*;
import com.google.gson.*;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/carts")

public class CartController {

    @GetMapping
    public String showUserCarts(HttpServletRequest request){
        if (!Authentication.isLogin(request)) return "Please login first";
        if (Authentication.isAdmin(Authentication.loggedInUser(request))) return "Make sure you are a costumer";
        User user = Authentication.loggedInUser(request);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List list = Stream.of(DataBase.getInstance().showUserCarts(user.getID())).collect(Collectors.toList());
        return gson.toJson(list); // converts to json
    }

    //hashmap to json?
    @GetMapping("/items")
    public String showItemsInCurrentCart(HttpServletRequest request){
        if (!Authentication.isLogin(request)) return "Please login first";
        if (Authentication.isAdmin(Authentication.loggedInUser(request))) return "Make sure you are a costumer";
        User user = Authentication.loggedInUser(request);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type gsonType = new TypeToken<HashMap>(){}.getType();
        return gson.toJson(DataBase.getInstance().getItemsInOpenCart(user.getID()),gsonType);
    }


    @PostMapping("/items/add")
    public String addItemToCart(@RequestParam long productId, HttpServletRequest request){
        if (!Authentication.isLogin(request)) return "Please login first";
        if (Authentication.isAdmin(Authentication.loggedInUser(request))) return "Make sure you are a costumer";
        User user = Authentication.loggedInUser(request);
        Cart cart = DataBase.getInstance().findOpenCartByUser(user.getID());
        DataBase.getInstance().addItem(cart.getID(), productId);
        return "Your item has been successfully added to cart.";
    }


    @PostMapping("/items/subtract")
    public String omitItemFromCart(@RequestParam long productId, HttpServletRequest request){
        if (!Authentication.isLogin(request)) return "Please login first";
        if (Authentication.isAdmin(Authentication.loggedInUser(request))) return "Make sure you are a costumer";
        User user = Authentication.loggedInUser(request);
        Cart cart = DataBase.getInstance().findOpenCartByUser(user.getID());
        DataBase.getInstance().decreaseItem(productId, cart.getID());
        return "Your item has been successfully subtracted from cart.";
    }


    @PostMapping("/items/delete")
    public String deleteItemFromCart(@RequestParam long productId, HttpServletRequest request){
        if (!Authentication.isLogin(request)) return "Please login first";
        if (Authentication.isAdmin(Authentication.loggedInUser(request))) return "Make sure you are a costumer";
        User user = Authentication.loggedInUser(request);
        Cart cart = DataBase.getInstance().findOpenCartByUser(user.getID());
        DataBase.getInstance().deleteItem(productId, cart.getID());
        return "Your item has been successfully deleted from cart.";
    }


    @PostMapping("/items/setq")
    public String setItemQuantityInCart(@RequestParam long productId, @RequestParam int quantity, HttpServletRequest request){
        if (!Authentication.isLogin(request)) return "Please login first";
        if (Authentication.isAdmin(Authentication.loggedInUser(request))) return "Make sure you are a costumer";
        User user = Authentication.loggedInUser(request);
        Cart cart = DataBase.getInstance().findOpenCartByUser(user.getID());
        DataBase.getInstance().setQuantityToAnItem(productId, cart.getID(),quantity);
        if(quantity < 1)
            return "Quantity must be greater than zero!";
        else if(quantity == 1)
            return "One " + DataBase.getInstance().findProduct(productId).getTitle() + "has been successfully added to cart.";
        else
            return quantity + " " + DataBase.getInstance().findProduct(productId).getTitle() + "s have been successfully added to cart.";
    }
}
