package org.acm.store.model;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import org.acm.store.controller.validation.CustomException;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

public class DataBase {

    private static DataBase instance;
    private long lastUserID, lastProductID, lastCommentID, lastCartID;
    private final HashMap<Long, User> users;
    private final HashMap<Long, Product> products;
    private final HashMap<Long, Comment> comments;
    private final HashMap<Long, Cart> carts;
    private final HashMap<Long, HashMap<Long, Integer>> items;

    private DataBase() {
        users = new HashMap<>();
        comments = new HashMap<>();
        products = new HashMap<>();
        carts = new HashMap<>();
        items = new HashMap<>();
        lastCartID = 0;
        lastCommentID = 0;
        lastProductID = 0;
        lastUserID = 0;
    }

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    public boolean isTaken(String email, String phoneNumber) {
        for (Map.Entry<Long, User> user : users.entrySet()) {
            if ((user.getValue().getEmail().equals(email)) || (user.getValue().getPhoneNumber().equals(phoneNumber))) {
                return true;
            }
        }
        return false;
    }

    public void addCostumer(String firstName, String lastName, String password,
                            String email, String phoneNumber, String address) {
        long ID = ++lastUserID;
        users.put(ID, new Costumer(ID, firstName, lastName, password, email, phoneNumber, address));
        createCart(ID);
    }

    public void addAdmin(String firstName, String lastName, String password,
                         String email, String phoneNumber, String address) {
        long ID = ++lastUserID;
        users.put(ID, new Admin(ID, firstName, lastName, password, email, phoneNumber, address));
    }

    public Comment findCommentByID(long id) {
        return comments.get(id);
    }

    public ArrayList<Comment> getAllComments() {
        return new ArrayList<>(comments.values());
    }

    public long validateUserByID(String email, String password) {
        for (Map.Entry<Long, User> user : users.entrySet()) {
            if ((user.getValue().getEmail().equals(email)) && (user.getValue().getPassword().equals(password))) {
                return user.getKey();
            }
        }
        return -1;
    }

    public User validateUser(String email, String password) {
        for (Map.Entry<Long, User> user : users.entrySet()) {
            if ((user.getValue().getEmail().equals(email)) && (user.getValue().getPassword().equals(password))) {
                return user.getValue();
            }
        }
        return null;
    }

    public User findUser(long ID) {
        return users.get(ID);
    }

    public HashMap<Long, Cart> getUserCarts(long userID) {
        HashMap<Long, Cart> cartHashMap = new HashMap<>();
        for (Map.Entry<Long, Cart> entry : carts.entrySet()) {
            if (entry.getValue().getUserID() == userID) cartHashMap.put(entry.getKey(), entry.getValue());
        }
        return cartHashMap;
    }

    public ArrayList<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    public ArrayList<Cart> showUserCarts(long userID) {
        HashMap<Long, Cart> cartHashMap = new HashMap<>();
        for (Map.Entry<Long, Cart> entry : carts.entrySet()) {
            if (entry.getValue().getUserID() == userID) cartHashMap.put(entry.getKey(), entry.getValue());
        }
        return new ArrayList<>(cartHashMap.values());
    }

    public ArrayList<Product> getProducts() {
        return new ArrayList<>(products.values());
    }

    public HashMap<Long, Comment> getUserComments(long userID) {
        HashMap<Long, Comment> commentHashMap = new HashMap<>();
        for (Map.Entry<Long, Comment> entry : comments.entrySet()) {
            if (entry.getValue().getUserID() == userID) commentHashMap.put(entry.getKey(), entry.getValue());
        }
        return commentHashMap;
    }

    public ArrayList<Comment> getProductComments(long productId) {
        ArrayList<Comment> userComments = new ArrayList<>();
        for (Map.Entry<Long, Comment> entry : comments.entrySet()) {
            if (entry.getValue().getProductID() == productId) userComments.add(entry.getValue());
        }
        return userComments;
    }

    public void addRatingToProduct(long productID, int rating) {
        if ((rating > 5) || (rating < 0)) throw new CustomException("Enter correct number");
        products.get(productID).addRating(rating);
    }

    public void addCredit(long ID, long amount) {
        if (amount <= 0) throw new CustomException("Enter correct amount");
        Costumer user = (Costumer) users.get(ID);
        user.addCredit(amount);
    }

    public void purchase(long userId) {
        Costumer user = (Costumer) users.get(userId);
        Cart cart = null;
        for (Map.Entry<Long, Cart> entry : getUserCarts(userId).entrySet()) {
            if (entry.getValue().getStatus() == Status.OPEN) cart = entry.getValue();
        }
        if (!user.hasEnoughCredit(cartPrice(cart.getID()))) {
            throw new CustomException("Not enough credit");
        }
        for (Map.Entry<Product, Integer> entry : getCartItems(cart.getID()).entrySet()) {
            //todo maybe quantities changed
            entry.getKey().setQuantityAvailable(entry.getKey().getQuantityAvailable() - entry.getValue());
        }
        user.purchase(cartPrice(cart.getID()));
        cart.setStatus(Status.CLOSED);
        createCart(user.getID());
    }

    public void addProduct(String title, String description, int quantityAvailable, int price, String category) {
        long ID = ++lastProductID;
        products.put(ID, new Product(ID, title, description, quantityAvailable, price, category));
    }

    public Product findProduct(long ID) {
        return products.get(ID);
    }

    public void updateProduct() {
        //todo many methods or if/else ?
    }

    public void addComment(long userID, long productID, String text) {
        long ID = ++lastCommentID;
        comments.put(ID, new Comment(ID, userID, productID, text));
    }

    public Comment findComment(long ID) {
        return comments.get(ID);
    }

    public void likeComment(long ID) {
        comments.get(ID).like();
    }

    public void dislikeComment(long ID) {
        comments.get(ID).dislike();
    }

    public void deleteComment(long ID) {
        comments.remove(ID);
    }

    public void createCart(long userID) {
        long ID = ++lastCartID;
        carts.put(ID, new Cart(ID, userID));
        items.put(ID, new HashMap<>());
    }

    public Cart findOpenCartByUser(long userId) {
        for (Map.Entry<Long, Cart> cart : carts.entrySet()) {
            if ((cart.getValue().getUserID() == userId) && (cart.getValue().getStatus() == Status.OPEN)) {
                return cart.getValue();
            }
        }
        return null;
    }

    public HashMap<Long, Integer> getItemsInOpenCart(long userId) {
        Cart cart = findOpenCartByUser(userId);
        if (items.get(cart.getID()) != null) {
            return items.get(cart.getID());
        }
        return null;
    }


    public Cart findCart(long ID) {
        return carts.get(ID);
    }

    public long cartPrice(long cartID) {
        long price = 0;
        for (Map.Entry<Product, Integer> entry : getCartItems(cartID).entrySet()) {
            price += entry.getKey().getPrice();
        }
        return price;
    }


    public void addItem(long cartID, long productID) {
        if (findProduct(productID).getQuantityAvailable() <= 0)
            throw new CustomException("Not enough quantity");
        if (getCartItems(cartID).containsKey(findProduct(productID))) {
            increaseItem(productID, cartID);
        } else {
            items.get(cartID).put(productID, 1);
        }
    }

    public void deleteItem(long productID, long cartID) {
        if (items.get(cartID) != null) {
            items.get(cartID).remove(productID);
        }
    }

    public void increaseItem(long productID, long cartID) {
        if (findProduct(productID).getQuantityAvailable() == items.get(cartID).get(productID))
            throw new CustomException("Not enough quantity");
        items.get(cartID).replace(productID, items.get(cartID).get(productID) + 1);
    }

    public void decreaseItem(long productID, long cartID) {
        if (items.get(cartID).get(productID) == 1) deleteItem(productID, cartID);
        else items.get(cartID).replace(productID, items.get(cartID).get(productID) - 1);
    }

    public void setQuantityToAnItem(long productID, long cartID, int quantity) {
        if (quantity <= 0) deleteItem(productID, cartID);
        else {
            if (getCartItems(cartID).containsKey(findProduct(productID))) {
                items.get(cartID).replace(productID, quantity);
            } else {
                items.get(cartID).put(productID, quantity);
            }
        }
    }

    public HashMap<Product, Integer> getCartItems(long cartID) {
        HashMap<Product, Integer> itemsHashMap = new HashMap<>();
        if (items.get(cartID) == null) {
            return null;
        }
        for (Map.Entry<Long, Integer> entry : items.get(cartID).entrySet()) {
            itemsHashMap.put(findProduct(entry.getKey()), entry.getValue());
        }
        return itemsHashMap;
    }
}
