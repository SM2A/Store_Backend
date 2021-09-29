package org.acm.store.controller;

import org.acm.store.controller.util.Authentication;
import org.acm.store.model.DataBase;
import org.acm.store.model.cart.Cart;
import org.acm.store.model.cartitem.CartItem;
import org.acm.store.model.user.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


@Validated
@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {

    @Autowired
    DataBase dataBase;

    @Autowired
    Authentication authentication;

    @PostMapping
    public String showUserCarts(@RequestParam(required = false) @NotBlank @Valid String password,
                                @RequestParam(required = false) @NotBlank @Valid String email) throws JSONException {

        User user = authentication.loggedInUser(email, password);
        JSONArray jsonArray = new JSONArray();
        for (Cart cart : dataBase.getUserCarts(user.getID())) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", cart.getID());
            jsonObject.put("userID", cart.getUserID());
            jsonObject.put("status", cart.getStatus());
            if (cart.getPurchaseDate() == null) jsonObject.put("purchaseDate", "OPEN");
            else jsonObject.put("purchaseDate", cart.getPurchaseDate());
            jsonObject.put("total", dataBase.cartPrice(cart.getID()));
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }

    @GetMapping("/all")
    public String getCarts() throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (Cart cart : dataBase.getCarts()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", cart.getID());
            jsonObject.put("userID", cart.getUserID());
            jsonObject.put("status", cart.getStatus());
            if (cart.getPurchaseDate() == null) jsonObject.put("purchaseDate", "OPEN");
            else jsonObject.put("purchaseDate", cart.getPurchaseDate());
            jsonObject.put("total", dataBase.cartPrice(cart.getID()));
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }

    @GetMapping("/{id}")
    public String getCartItems(@PathVariable("id") long id) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (CartItem item : dataBase.getCartItems(id)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", item.getProductID());
            jsonObject.put("title", dataBase.findProduct(item.getProductID()).getTitle());
            jsonObject.put("category", dataBase.findProduct(item.getProductID()).getCategory());
            jsonObject.put("price", dataBase.findProduct(item.getProductID()).getPrice());
            jsonObject.put("quantity", item.getCount());
            jsonObject.put("total", dataBase.findProduct(item.getProductID()).getPrice() * item.getCount());
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }

    /*@GetMapping("/items")
    public HashMap<Long, Integer> showItemsInCurrentCart(HttpServletRequest request) {
        if (!Authentication.isLogin(request))
            throw new CustomException("Please login first");
        if (Authentication.isAdmin(Authentication.loggedInUser(request)))
            throw new CustomException("Make sure you are a costumer");
        User user = Authentication.loggedInUser(request);
        return dataBase.getItemsInOpenCart(user.getID());
    }*/

    @PostMapping("/items/add")
    public String addItemToCart(@RequestParam(required = false) @NotBlank @Valid String password,
                                @RequestParam(required = false) @NotBlank @Valid String email,
                                @RequestParam(required = false) @NotBlank @Valid String productId) {

        User user = authentication.loggedInUser(email, password);
        Cart cart = dataBase.findOpenCartByUser(user.getID());
        dataBase.addItem(cart.getID(), Long.parseLong(productId));
        return "Your item has been successfully added to cart";
    }

    @PostMapping("/items/subtract")
    public String omitItemFromCart(@RequestParam(required = false) @NotBlank @Valid String password,
                                   @RequestParam(required = false) @NotBlank @Valid String email,
                                   @RequestParam(required = false) @NotBlank @Valid String productId) {

        User user = authentication.loggedInUser(email, password);
        Cart cart = dataBase.findOpenCartByUser(user.getID());
        dataBase.decreaseItem(Long.parseLong(productId), cart.getID());
        return "Your item has been successfully subtracted from cart";
    }

    @PostMapping("/items/delete")
    public String deleteItemFromCart(@RequestParam(required = false) @NotBlank @Valid String password,
                                     @RequestParam(required = false) @NotBlank @Valid String email,
                                     @RequestParam(required = false) @NotBlank @Valid String productId) {

        User user = authentication.loggedInUser(email, password);
        Cart cart = dataBase.findOpenCartByUser(user.getID());
        dataBase.deleteItem(Long.parseLong(productId), cart.getID());
        return "Your item has been successfully deleted from cart";
    }

    /*@PostMapping("/items/setq")
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
        Cart cart = dataBase.findOpenCartByUser(user.getID());
        dataBase.setQuantityToAnItem(Long.parseLong(productId), cart.getID(), Integer.parseInt(quantity));
        if (Integer.parseInt(quantity) == 1)
            return "One " + dataBase.findProduct(Long.parseLong(productId)).getTitle() +
                    "has been successfully added to cart.";
        else
            return quantity + " " + dataBase.findProduct(Long.parseLong(productId)).getTitle() +
                    "s have been successfully added to cart.";
    }*/
}
