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

    public HashMap<Long,Cart> getUserCarts(long userID){
        HashMap<Long,Cart> cartHashMap = new HashMap<>();
        for (Map.Entry<Long,Cart> entry : carts.entrySet()) {
            if (entry.getValue().getUserID() == userID) cartHashMap.put(entry.getKey(),entry.getValue());
        }
        return cartHashMap;
    }

    public HashMap<Long,Comment> getUserComments(long userID){
        HashMap<Long,Comment> commentHashMap = new HashMap<>();
        for (Map.Entry<Long,Comment> entry : comments.entrySet()) {
            if (entry.getValue().getUserID() == userID) commentHashMap.put(entry.getKey(),entry.getValue());
        }
        return commentHashMap;
    }

    public void addCredit(long ID, long amount) {
        Costumer user = (Costumer) users.get(ID);
        user.addCredit(amount);
    }

    public void purchase(long ID, long amount) {
        Costumer user = (Costumer) users.get(ID);
        user.purchase(amount);
        Cart cart = null;
        for (Map.Entry<Long,Cart> entry : getUserCarts(ID).entrySet()){
            if (entry.getValue().getStatus() == Status.OPEN) cart = entry.getValue();
        }
        cart.setStatus(Status.CLOSED);
        for (Map.Entry<Product,Integer> entry : getCartItems(cart.getID()).entrySet()){
            decreaseItem(entry.getKey().getID(),entry.getValue());
        }
    }

    public void addProduct(String title, String description, int quantityAvailable, int price, Category category) {
        long ID = ++lastProductID;
        products.put(ID, new Product(ID, title, description, quantityAvailable, price, category));
    }

    public Product findProduct(long ID) {
        return products.get(ID);
    }

    private void decreaseQuantity(long productID , int count){
        products.get(productID).setQuantityAvailable(products.get(productID).getQuantityAvailable()-count);
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

    public void likeComment(long ID){
        comments.get(ID).like();
    }

    public void dislikeComment(long ID){
        comments.get(ID).dislike();
    }

    public void deleteComment(long ID) {
        comments.remove(ID);
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

    public HashMap<Product, Integer> getCartItems(long cartID){
        HashMap<Product, Integer> itemsHashMap = new HashMap<>();
        for (Map.Entry<Long,Integer> entry : items.get(cartID).entrySet()){
            itemsHashMap.put(findProduct(entry.getKey()),entry.getValue());
        }
        return itemsHashMap;
    }
}
