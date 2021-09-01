package org.acm.store.model;

import org.acm.store.controller.validation.CustomException;

public class Product {

    private final long ID;
    private String title;
    private String description;
    private int quantityAvailable;
    private float rating;
    private long price;
    private String category;
    private String imgAddress;

    public Product(long id, String title, String description, int quantityAvailable, long price, String category, String imgAddress) {
        this.ID = id;
        this.title = title;
        this.description = description;
        this.quantityAvailable = quantityAvailable;
        this.rating = 0;
        this.price = price;
        this.category = category;
        this.imgAddress = imgAddress;
        //imgAddress = "https://images.unsplash.com/photo-1508423134147-addf71308178?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=751&q=80";
    }

    private String getCategoryByString(String category) {
        //if(category == "X")
        return this.category;
        //for all categories...
    }

    public void takeFromStock(int quantity) {
        if (quantityAvailable - quantity >= 0) {
            quantityAvailable -= quantity;
        } else {
            System.out.println("ERROR! Out Of Stock.");
            throw new CustomException("ERROR! Out Of Stock.");
        }
    }

    public void addRating(int newRating) {//rate function???
        if (newRating <= 5) {
            rating = (rating + newRating) / 2;
        }
    }

    public void addToStock(int quantity) {
        if (quantity <= 0) throw new CustomException("Enter correct amount");
        quantityAvailable += quantity;
    }

    public long getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public float getRating() {
        return rating;
    }

    public long getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }

    public String getImgAddress() {
        return imgAddress;
    }
}