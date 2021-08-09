package model;

public class Comment {

    private String commentID;
    private String userID;
    private String productID;
    private String text;
    private int likes;
    private int dislikes;

    public Comment(String commentID, String userID, String productID, String text) {
        this.commentID = commentID;
        this.userID = userID;
        this.productID = productID;
        this.text = text;
        likes = 0;
        dislikes = 0;
    }

    public String getCommentID() {
        return commentID;
    }

    public String getUserID() {
        return userID;
    }

    public String getProductID() {
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
