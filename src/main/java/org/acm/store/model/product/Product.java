package org.acm.store.model.product;

import org.acm.store.controller.util.CustomException;

import javax.persistence.*;

@Table(name = "product", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID"),
        @UniqueConstraint(columnNames = "Title")
})
@Entity(name = "product")
@NamedQueries({
        @NamedQuery(name = Product.GET_PRODUCT_BY_TITLE_CATEGORY, query = Product.GET_PRODUCT_BY_TITLE_CATEGORY_Q),
        @NamedQuery(name = Product.GET_PRODUCT_BY_TITLE, query = Product.GET_PRODUCT_BY_TITLE_Q),
        @NamedQuery(name = Product.GET_PRODUCT_BY_CATEGORY, query = Product.GET_PRODUCT_BY_CATEGORY_Q)
})
public class Product {

    public static final String GET_PRODUCT_BY_TITLE_CATEGORY = "GET_PRODUCT_BY_TITLE_CATEGORY";
    public static final String GET_PRODUCT_BY_TITLE_CATEGORY_Q
            = "FROM product p WHERE p.title = :title AND p.category = :category";

    public static final String GET_PRODUCT_BY_TITLE = "GET_PRODUCT_BY_TITLE";
    public static final String GET_PRODUCT_BY_TITLE_Q
            = "FROM product p WHERE p.title = :title";

    public static final String GET_PRODUCT_BY_CATEGORY = "GET_PRODUCT_BY_CATEGORY";
    public static final String GET_PRODUCT_BY_CATEGORY_Q
            = "FROM product p WHERE p.category = :category";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private long ID;
    @Column(name = "Title", nullable = false)
    private String title;
    @Column(name = "info", nullable = false)
    private String description;
    @Column(name = "Quantity", nullable = false)
    private int quantityAvailable;
    @Column(name = "Rating", nullable = false)
    private float rating;
    @Column(name = "Price", nullable = false)
    private long price;
    @Column(name = "Category", nullable = false)
    private String category;
    @Column(name = "ImageAddress", nullable = false)
    private String imgAddress;
    @Column(name = "RateCount", nullable = false)
    private int rateCount;

    public Product() {}

    public Product(String title,
                   String description, int quantityAvailable, long price, String category, String imgAddress) {
        this.title = title;
        this.description = description;
        this.quantityAvailable = quantityAvailable;
        this.rating = 0;
        this.price = price;
        this.category = category;
        this.imgAddress = imgAddress;
        this.rateCount = 0;
    }

    public void addRating(int newRating) {
        if (rating == 0) {
            rating = newRating;
            rateCount++;
        } else {
            float sum = (float) rateCount * rating;
            sum += (float) newRating;
            rateCount++;
            rating = sum / (float) rateCount;
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