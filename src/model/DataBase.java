package model;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

public class DataBase {

    private HashMap<String,User> users;
    private HashMap<String,Product> products;
    private HashMap<String,Comment> comments;
    private HashMap<String,Cart> carts;
    private HashMap<String, ArrayList<AbstractMap.SimpleEntry<String,Integer>>> orders;

    public DataBase() {
        users = new HashMap<String, User>();
        comments = new HashMap<String, Comment>();
        products = new HashMap<String, Product>();
        carts = new HashMap<String, Cart>();
        orders = new HashMap<>();
    }

    public void addUser(){

    }

    public User findUser(){

    }

}
