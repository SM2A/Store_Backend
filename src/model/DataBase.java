package model;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

public class DataBase {

    private long lastUserID , lastProductID , lastCommentID , lastCartID ;
    private HashMap<Long, User> users;
    private HashMap<Long, Product> products;
    private HashMap<Long, Comment> comments;
    private HashMap<Long, Cart> carts;
    private HashMap<Long, ArrayList<AbstractMap.SimpleEntry<String, Integer>>> orders;

    public DataBase() {
        users = new HashMap<>();
        comments = new HashMap<>();
        products = new HashMap<>();
        carts = new HashMap<>();
        orders = new HashMap<>();
        lastCartID = 0;
        lastCommentID = 0;
        lastProductID = 0;
        lastUserID = 0;
    }

    public void addCustomer(String firstName, String lastName, String password,
                            String email, String phoneNumber, String address) {
        long ID = ++lastUserID;
        users.put(ID,new Costumer(ID,firstName,lastName,password,email,phoneNumber,address));
        //todo creat open cart
    }

    public void addAdmin(String firstName, String lastName, String password,
                         String email, String phoneNumber, String address) {
        long ID = ++lastUserID;
        users.put(ID,new Admin(ID,firstName,lastName,password,email,phoneNumber,address));
    }

    public User findUser(long ID) {
        return users.get(ID);
    }
    
    public void addCredit(long ID , long amount) {
        Costumer user = (Costumer) users.get(ID);
        user.addCredit(amount);
    }
    
    public void payCart(long ID , long amount) {
        Costumer user = (Costumer) users.get(ID);
        user.purchase(amount);
    }

    public void addProduct(String title, String description, int quantityAvailable, int price, Category category) {
        long ID = ++lastProductID;
        products.put(ID,new Product(ID,title,description,quantityAvailable,price,category));
    }

    public Product findProduct(long ID) {
        return products.get(ID);
    }

    public void updateProduct() {
        //todo many methods or if/else ?
    }

    public void addComment(long userID, long productID, String text) {
        long ID = ++lastCommentID;
        comments.put(ID,new Comment(ID,userID,productID,text));
    }

    public Comment findComment(long ID) {
        return comments.get(ID);
    }

    public void updateComment() {
        //todo many methods or if/else ?
    }

    private void createCart() {

    }

    private Cart findCart() {

    }

    private void updateCart() {

    }
}
