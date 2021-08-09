package model;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

public class DataBase {

    private long lastUserID, lastProductID, lastCommentID, lastCartID;
    private final HashMap<Long, User> users;
    private final HashMap<Long, Product> products;
    private final HashMap<Long, Comment> comments;
    private final HashMap<Long, Cart> carts;
    private final HashMap<Long, HashMap<Long, Integer>> items;

    public DataBase() {
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

    public void addCustomer(String firstName, String lastName, String password,
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

    public long validateUser(String email, String password) {
        for (Map.Entry<Long, User> user : users.entrySet()) {
            if ((user.getValue().getEmail().equals(email)) && (user.getValue().getPassword().equals(password))) {
                return user.getKey();
            }
        }
        return -1;
    }

    public User findUser(long ID) {
        return users.get(ID);
    }

    public void addCredit(long ID, long amount) {
        Costumer user = (Costumer) users.get(ID);
        user.addCredit(amount);
    }

    public void purchase(long ID, long amount) {
        Costumer user = (Costumer) users.get(ID);
        user.purchase(amount);
    }

    public void addProduct(String title, String description, int quantityAvailable, int price, Category category) {
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

    public void updateComment() {
        //todo many methods or if/else ?
    }

    private void createCart(long userID) {
        long ID = ++lastCartID;
        carts.put(ID, new Cart(userID));
        items.put(ID, new HashMap<>());
    }

    public Cart findCart(long ID) {
        return carts.get(ID);
    }

    public void deleteItem(long productID, long cartID) {
        items.get(cartID).remove(productID);
    }

    public void increaseItem(long productID, long cartID) {
        items.get(cartID).replace(productID, items.get(cartID).get(productID) + 1);
    }

    public void decreaseItem(long productID, long cartID) {
        if (items.get(cartID).get(productID) == 1) deleteItem(productID, cartID);
        else items.get(cartID).replace(productID, items.get(cartID).get(productID) - 1);
    }
}
