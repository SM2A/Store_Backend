package model;

import java.util.Map;
import java.util.Date;
import java.util.HashMap;

class Cart {

    private int ID;
    private Map<Long, Integer> items;// (pruductID, quantity)
    //todo get rid of 'items'
    private float totalPrice;
    private int totalCount;
    private Status status;
    private int userID;
    private Date purchaseDate;

    public Cart(Map<Integer, Integer> items, float totalPrice, int totalCount, Status status, int userID) {
        items = new HashMap<>();
        this.totalPrice = 0;
        this.totalCount = 0;
        this.status = status;
        this.userID = userID;
    }

    public void addItem(Product product, int quantity) {
        status = Status.OPEN;
        long productID = product.getID();
        if (items.containsKey(productID)) {
            for (Map.Entry<Long, Integer> entry : items.entrySet()) {
                if (entry.getKey() == productID) {
                    entry.setValue(entry.getValue() + quantity);
                }
            }
        } else {
            items.put(productID, quantity);
        }
        totalPrice += quantity * product.getPrice();
        totalCount += 1;
        System.out.println("successfully Added.");
    }

    public void deleteItem(Product product, int quantity) {
        long productID = product.getID();
        if (items.containsKey(productID)) {
            if (quantity < items.get(productID)) {
                System.out.println("ERROR! Quantity Is Out Of Range.");
                return;
            }
            items.remove(productID);
            totalCount -= 1;
            totalPrice -= quantity * product.getPrice();
            if (totalCount == 0) {
                status = Status.CLOSED;
            }
            System.out.println("successfully Deleted.");
        } else {
            System.out.println("ERROR! Item Is Not In Your Cart.");
        }
    }

    public void purchase() {
        totalPrice = 0;
        items.clear();
        status = Status.CLOSED;
    }

    public int getID() {
        return ID;
    }

    public Map<Long, Integer> getItems() {
        return items;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Status getStatus() {
        return status;
    }

    public int getUserID() {
        return userID;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}