public class Product {
    private int productId;
    private String title;
    private String description;
    private int quantityAvailable;
    private float rating;
    private float price;
    private Category category;
    private Date purchaseDate;

    public Product(int id, String title, String description, int quantityAvailable, int price, Category category) {
        this.productId = id;
        this.title = title;
        this.description = description;
        this.quantityAvailable = quantityAvailable;
        this.rating = 0;
        this.price = price;
        this.category = category;
        purchaseDate = new Date();
        purchaseDate.getTime();
    }

    public void takeFromStock(int quantity){
        if(quantityAvailable - quantity >= 0){
            quantityAvailable -= quantity;
        }
        else {
            System.out.println("ERROR! Out Of Stock.");
        }
    }

    public void addRating(int newRating){
        if(newRating <= 5){
            rating = (rating + newRating)/2;
        }
    }

    public void addToStock(int quantity){
        quantityAvailable += quantity;
    }

    public int getProductId() {
        return productId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public float getRating() {
        return rating;
    }

    public float getPrice() {
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

    public void setPrice(float price) {
        this.price = price;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }
}