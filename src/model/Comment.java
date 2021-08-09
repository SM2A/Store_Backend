package model;

class Comment {

    private long ID;
    private long userID;
    private long productID;
    private String text;
    private int likes;
    private int dislikes;

    public Comment(long ID, long userID, long productID, String text) {
        this.ID = ID;
        this.userID = userID;
        this.productID = productID;
        this.text = text;
        likes = 0;
        dislikes = 0;
    }

    public long getID() {
        return ID;
    }

    public long getUserID() {
        return userID;
    }

    public long getProductID() {
        return productID;
    }

    public String getText() {
        return text;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void like() {
        likes++;
    }

    public void dislike() {
        dislikes++;
    }
}
