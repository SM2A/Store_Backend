package org.acm.store.controller;


import org.acm.store.controller.validation.Authentication;
import org.acm.store.controller.validation.CustomException;
import org.acm.store.model.Cart;
import org.acm.store.model.DataBase;
import org.acm.store.model.User;
import java.util.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


@Validated
@RestController
@RequestMapping("/carts")
public class CartController {

    @GetMapping
    public ArrayList<Cart> showUserCarts(HttpServletRequest request) {
        if (!Authentication.isLogin(request))
            throw new CustomException("Please login first");
        if (Authentication.isAdmin(Authentication.loggedInUser(request)))
            throw new CustomException("Make sure you are a costumer");
        User user = Authentication.loggedInUser(request);
        return DataBase.getInstance().showUserCarts(user.getID());
    }


    @GetMapping("/items")
    public HashMap<Long, Integer> showItemsInCurrentCart(HttpServletRequest request) {
        if (!Authentication.isLogin(request))
            throw new CustomException("Please login first");
        if (Authentication.isAdmin(Authentication.loggedInUser(request)))
            throw new CustomException("Make sure you are a costumer");
        User user = Authentication.loggedInUser(request);
        return DataBase.getInstance().getItemsInOpenCart(user.getID());
    }


    @PostMapping("/items/add")
    public String addItemToCart(@RequestParam(required = false) @NotBlank @Valid String productId,
                                HttpServletRequest request){
        if (!Authentication.isLogin(request))
            throw new CustomException("Please login first");;
        if (Authentication.isAdmin(Authentication.loggedInUser(request)))
            throw new CustomException("Make sure you are a costumer");
        User user = Authentication.loggedInUser(request);
        Cart cart = DataBase.getInstance().findOpenCartByUser(user.getID());
        DataBase.getInstance().addItem(cart.getID(), Long.parseLong(productId));
        return "Your item has been successfully added to cart.";
    }


    @PostMapping("/items/subtract")
    public String omitItemFromCart(@RequestParam(required = false) @NotBlank @Valid String productId,
                                   HttpServletRequest request){
        if (!Authentication.isLogin(request))
            throw new CustomException("Please login first");
        if (Authentication.isAdmin(Authentication.loggedInUser(request)))
            throw new CustomException("Make sure you are a costumer");
        User user = Authentication.loggedInUser(request);
        Cart cart = DataBase.getInstance().findOpenCartByUser(user.getID());
        DataBase.getInstance().decreaseItem(Long.parseLong(productId), cart.getID());
        return "Your item has been successfully subtracted from cart.";
    }


    @PostMapping("/items/delete")
    public String deleteItemFromCart(@RequestParam(required = false) @NotBlank @Valid String productId,
                                     HttpServletRequest request) {
        if (!Authentication.isLogin(request))
            throw new CustomException("Please login first");
        if (Authentication.isAdmin(Authentication.loggedInUser(request)))
            throw new CustomException("Make sure you are a costumer");
        User user = Authentication.loggedInUser(request);
        Cart cart = DataBase.getInstance().findOpenCartByUser(user.getID());
        DataBase.getInstance().deleteItem(Long.parseLong(productId), cart.getID());
        return "Your item has been successfully deleted from cart.";
    }


    @PostMapping("/items/setq")
    public String setItemQuantityInCart(@RequestParam(required = false) @NotBlank @Valid String productId,
                                        @RequestParam(required = false) @NotBlank @Valid String quantity,
                                        HttpServletRequest request) {
        if (!Authentication.isLogin(request))
            throw new CustomException("Please login first");
        if (Authentication.isAdmin(Authentication.loggedInUser(request)))
            throw new CustomException("Please login first");
        if (Integer.parseInt(quantity) < 1)
            throw new CustomException("Quantity must be greater than zero!");
        User user = Authentication.loggedInUser(request);
        Cart cart = DataBase.getInstance().findOpenCartByUser(user.getID());
        DataBase.getInstance().setQuantityToAnItem(Long.parseLong(productId), cart.getID(), Integer.parseInt(quantity));
        if (Integer.parseInt(quantity) == 1)
            return "One " + DataBase.getInstance().findProduct(Long.parseLong(productId)).getTitle() +
                    "has been successfully added to cart.";
        else
            return quantity + " " + DataBase.getInstance().findProduct(Long.parseLong(productId)).getTitle() +
                    "s have been successfully added to cart.";
    }
}
