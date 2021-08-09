package model;

import java.util.Map;
import java.util.Date;
import java.util.HashMap;

class Cart {

    private long ID;
    private Status status;
    private long userID;
    private Date purchaseDate;

    public Cart(long userID) {
        this.status = Status.OPEN;
        this.userID = userID;
    }

    public long getID() {
        return ID;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
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

    public long getUserID() {
        return userID;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}