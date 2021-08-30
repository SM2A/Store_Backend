package org.acm.store.controller;


import org.acm.store.controller.validation.Authentication;
import org.acm.store.controller.validation.CustomException;
import org.acm.store.model.Cart;
import org.acm.store.model.DataBase;
import org.acm.store.model.Product;
import org.acm.store.model.User;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


@Validated
@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("/all")
    public String getCarts() throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (Cart cart : DataBase.getInstance().getCarts()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", cart.getID());
            jsonObject.put("userID", cart.getUserID());
            jsonObject.put("status", cart.getStatus());
            if (cart.getPurchaseDate() == null) jsonObject.put("purchaseDate", "OPEN");
            else jsonObject.put("purchaseDate", cart.getPurchaseDate());
            jsonObject.put("total", DataBase.getInstance().cartPrice(cart.getID()));
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }

    @GetMapping("/{id}")
    public String getCartItems(@PathVariable("id") long id) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (Map.Entry<Product, Integer> entry : DataBase.getInstance().getCartItems(id).entrySet()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", entry.getKey().getID());
            jsonObject.put("title", entry.getKey().getTitle());
            jsonObject.put("category", entry.getKey().getCategory());
            jsonObject.put("price", entry.getKey().getPrice());
            jsonObject.put("quantity", entry.getValue());
            jsonObject.put("total", entry.getKey().getPrice() * entry.getValue());
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
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
                                HttpServletRequest request) {
        if (!Authentication.isLogin(request))
            throw new CustomException("Please login first");
        if (Authentication.isAdmin(Authentication.loggedInUser(request)))
            throw new CustomException("Make sure you are a costumer");
        User user = Authentication.loggedInUser(request);
        Cart cart = DataBase.getInstance().findOpenCartByUser(user.getID());
        DataBase.getInstance().addItem(cart.getID(), Long.parseLong(productId));
        return "Your item has been successfully added to cart.";
    }


    @PostMapping("/items/subtract")
    public String omitItemFromCart(@RequestParam(required = false) @NotBlank @Valid String productId,
                                   HttpServletRequest request) {
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
