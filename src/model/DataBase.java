package model;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

public class DataBase {

    private HashMap<String, User> users;
    private HashMap<String, Product> products;
    private HashMap<String, Comment> comments;
    private HashMap<String, Cart> carts;
    private HashMap<String, ArrayList<AbstractMap.SimpleEntry<String, Integer>>> orders;

    public DataBase() {
        users = new HashMap<>();
        comments = new HashMap<>();
        products = new HashMap<>();
        carts = new HashMap<>();
        orders = new HashMap<>();
    }

    public void addUser() {

    }

    public User findUser() {

    }

    public void addProduct() {

    }

    public Product findProduct() {

    }

    public void updateProduct() {

    }

    public void addComment() {

    }

    public Comment findComment() {

    }

    public void updateComment() {

    }

    private void createCart() {

    }

    private Cart findCart() {

    }

    private void updateCart() {

    }
}
