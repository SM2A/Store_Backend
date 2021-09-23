package org.acm.store.model;

import java.util.*;
import org.acm.store.controller.util.CustomException;
import org.acm.store.model.cart.*;
import org.acm.store.model.cartitem.CartItem;
import org.acm.store.model.cartitem.CartItemService;
import org.acm.store.model.category.Category;
import org.acm.store.model.category.CategoryService;
import org.acm.store.model.comment.Comment;
import org.acm.store.model.comment.CommentService;
import org.acm.store.model.product.Product;
import org.acm.store.model.product.ProductService;
import org.acm.store.model.user.User;
import org.acm.store.model.user.admin.Admin;
import org.acm.store.model.user.admin.AdminService;
import org.acm.store.model.user.customer.Costumer;
import org.acm.store.model.user.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

@Component
public class DataBase {

    @Autowired
    AdminService adminService;

    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;

    @Autowired
    CommentService commentService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CartService cartService;

    @Autowired
    CartItemService itemService;

    public DataBase() {
    }

    private boolean isTaken(String email, String phoneNumber) {
        Costumer costumer = customerService.getCustomerEmailPhoneNumber(email, phoneNumber);
        Admin admin = adminService.getAdminEmailPhoneNumber(email, phoneNumber);
        return costumer != null || admin != null;
    }

    private boolean isTaken(long id, String email, String phoneNumber) {
        Costumer costumer = customerService.getCustomerEmailPhoneNumber(id, email, phoneNumber);
        Admin admin = adminService.getAdminEmailPhoneNumber(id, email, phoneNumber);
        return costumer != null || admin != null;
    }

    public long addCostumer(String firstName, String lastName, String password,
                            String email, String phoneNumber, String address) {
        if (isTaken(email, phoneNumber)) throw new CustomException("This email or phone-number is taken");
        long id = customerService.addCustomer(new Costumer(firstName, lastName, password, email, phoneNumber, address));
        createCart(id);
        return id;
    }

    public long addAdmin(String firstName, String lastName, String password,
                         String email, String phoneNumber, String address) {
        if (isTaken(email, phoneNumber)) throw new CustomException("This email or phone-number is taken");
        return adminService.addAdmin(new Admin(firstName, lastName, password, email, phoneNumber, address));
    }

    public void editUser(long id, String firstName, String lastName, String email,
                         String phoneNumber, String address) {
        Costumer customer = customerService.getCustomer(id);
        if (customer == null) {
            Admin admin = adminService.getAdmin(id);
            if (isTaken(id, email, phoneNumber)) throw new CustomException("This email or phone-number is taken");
            admin.setFirstName(firstName);
            admin.setLastName(lastName);
            admin.setEmail(email);
            admin.setPhoneNumber(phoneNumber);
            admin.setAddress(address);
            adminService.updateAdmin(admin);
        }
        if (isTaken(id, email, phoneNumber)) throw new CustomException("This email or phone-number is taken");
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        customer.setAddress(address);
        customerService.updateCustomer(customer);
    }

    public void changePassword(long id, String password) {
        Costumer customer = customerService.getCustomer(id);
        if (customer == null) {
            Admin admin = adminService.getAdmin(id);
            admin.setPassword(password);
            adminService.updateAdmin(admin);
        }
        customer.setPassword(password);
        customerService.updateCustomer(customer);
    }

    public Comment findCommentByID(long id) {
        return commentService.getComment(id);
    }

    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    public User validateUser(String email, String password) {
        Costumer costumer = customerService.getCustomerEmailPassword(email, password);
        if (costumer == null) return adminService.getAdminEmailPassword(email, password);
        return costumer;
    }

    public User findUser(long ID) {
        Costumer costumer = customerService.getCustomer(ID);
        if (costumer == null) return adminService.getAdmin(ID);
        return costumer;
    }

    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        userList.addAll(adminService.getAllAdmins());
        userList.addAll(customerService.getAllCustomers());
        return userList;
    }

    public List<Cart> getUserCarts(long userID) {
        List<Cart> list = new ArrayList<>();
        list.addAll(cartService.getCart(userID, Status.OPEN));
        list.addAll(cartService.getCart(userID, Status.CLOSED));
        return list;
    }

    public List<Product> getProducts() {
        return productService.getAllProduct();
    }

    public void editProduct(long id, String title, String description, int quantityAvailable,
                            int price, String category, String imgAddress) {
        Product product = productService.getProduct(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setQuantityAvailable(quantityAvailable);
        product.setPrice(price);
        product.setCategory(category);
        product.setImgAddress(imgAddress);
        productService.updateProduct(product);
    }

    public List<Comment> getProductComments(long productId) {
        return commentService.getProductComments(productId);
    }

    public void addRatingToProduct(long productID, int rating) {
        if ((rating > 5) || (rating < 0)) throw new CustomException("Enter correct number");
        Product product = productService.getProduct(productID);
        product.addRating(rating);
        productService.updateProduct(product);
    }

    public void addCredit(long ID, long amount) {
        if (amount <= 0) throw new CustomException("Enter correct amount");
        Costumer costumer = customerService.getCustomer(ID);
        costumer.addCredit(amount);
        customerService.updateCustomer(costumer);
    }

    public void purchase(long userId) {
        Costumer user = customerService.getCustomer(userId);
        Cart cart = cartService.getCart(userId, Status.OPEN).get(0);
        if (!user.hasEnoughCredit(cartPrice(cart.getID()))) {
            throw new CustomException("Not enough credit");
        }
        for (CartItem item : itemService.getCartItems(cart.getID())) {
            //todo maybe quantities changed
            Product product = productService.getProduct(item.getProductID());
            product.setQuantityAvailable(product.getQuantityAvailable() - item.getCount());
            productService.updateProduct(product);
        }
        user.purchase(cartPrice(cart.getID()));
        cart.purchase();
        cart.setStatus(Status.CLOSED);
        createCart(user.getID());
        customerService.updateCustomer(user);
        cartService.updateCart(cart);
    }

    public long addProduct(String title, String description, int quantityAvailable,
                           int price, String category, String imgAddress) {

        if (getExistedProduct(title, category) != null) {
            Product product = getExistedProduct(title, category);
            product.addToStock(quantityAvailable);
            productService.updateProduct(product);
            return product.getID();
        } else {
            if (!isCategoryAvailable(category)) throw new CustomException("Category not available");
            return productService
                    .addProduct(new Product(title, description, quantityAvailable, price, category, imgAddress));
        }
    }

    public Product findProduct(long ID) {
        return productService.getProduct(ID);
    }

    public void addComment(long userID, long productID, String text) {
        commentService.addComment(new Comment(userID, productID, text));
    }

    public Comment findComment(long ID) {
        return commentService.getComment(ID);
    }

    public void likeComment(long ID) {
        Comment comment = commentService.getComment(ID);
        comment.like();
        commentService.updateComment(comment);
    }

    public void dislikeComment(long ID) {
        Comment comment = commentService.getComment(ID);
        comment.dislike();
        commentService.updateComment(comment);
    }

    public void deleteComment(long ID) {
        commentService.deleteComment(ID);
    }

    public void createCart(long userID) {
        cartService.addCart(new Cart(userID));
    }

    public List<Cart> getCarts() {
        return cartService.getAllCart();
    }

    public Cart findOpenCartByUser(long userId) {
        return cartService.getCart(userId, Status.OPEN).get(0);
    }

    public List<CartItem> getItemsInOpenCart(long userId) {
        Cart cart = findOpenCartByUser(userId);
        return itemService.getCartItems(cart.getID());
    }

    public Cart findCart(long ID) {
        return cartService.getCart(ID);
    }

    public long cartPrice(long cartID) {
        return itemService.getCartPrice(cartID);
    }

    public void addItem(long cartID, long productID) {
        if (findProduct(productID).getQuantityAvailable() <= 0)
            throw new CustomException("Not enough quantity");
        if (getCartItems(cartID).stream().filter(item -> item.getProductID() == productID)
                .findAny().orElse(null) != null) {
            increaseItem(productID, cartID);
        } else {
            itemService.addItem(new CartItem(cartID, productID, 1));
        }
    }

    public void deleteItem(long productID, long cartID) {
        if (itemService.getItem(cartID, productID) == null)
            throw new CustomException("Your cart doesn't contain item with id: " + productID);
        itemService.deleteItem(cartID, productID);
    }

    public void increaseItem(long productID, long cartID) {
        CartItem item = itemService.getItem(cartID, productID);
        if (findProduct(productID).getQuantityAvailable() == item.getCount())
            throw new CustomException("Not enough quantity");
        item.setCount(item.getCount() + 1);
        itemService.updateItem(item);
    }

    public void decreaseItem(long productID, long cartID) {
        CartItem item = itemService.getItem(cartID, productID);
        if (item == null) throw new CustomException("Your cart doesn't contain item with id: " + productID);
        if (item.getCount() <= 1) itemService.deleteItem(item.getCartID(), item.getProductID());
        else {
            item.setCount(item.getCount() - 1);
            itemService.updateItem(item);
        }
    }

    public List<CartItem> getCartItems(long cartID) {
        return itemService.getCartItems(cartID);
    }

    public Product getExistedProduct(String title, String category) {
        return productService.getProduct(title, new Category(category));
    }

    private boolean isCategoryAvailable(String name) {
        return categoryService.getCategory(name.toUpperCase()) != null;
    }

    public void addCategory(String name) {
        if (isCategoryAvailable(name)) throw new CustomException("This category was added");
        else {
            categoryService.addCategory(new Category(name.toUpperCase()));
        }
    }

    public List<Category> getCategories() {
        return categoryService.getAllCategories();
    }

    public List<Product> searchProductsByName(String name) {
        return productService.getProduct(name);
    }

    public List<Product> searchProductsByCategory(String category) {
        return productService.getProduct(new Category(category));
    }

    public List<ArrayList<Product>> getMainProducts() {//Randomly get 4 products for 3 categories to show in  home page
        List<ArrayList<Product>> allMainProducts = new ArrayList<>();
        if (categoryService.getAllCategories().size() < 3)
            throw new CustomException("Not Enough Categories!\r\ntry localhost:8080/test.");
        for (int j = 0; j < 3; j++) {
            ArrayList<Product> mainProductsInACategory = new ArrayList<>();
            List<Product> list = productService.getProduct(categoryService.getAllCategories().get(j));
            Collections.shuffle(list);
            if (list.size() < 4) throw new CustomException("Not Enough Categories!\r\ntry localhost:8080/test.");
            for (int i = 0; i < 4; i++) {
                mainProductsInACategory.add(list.get(i));
            }
            allMainProducts.add(mainProductsInACategory);
        }
        return allMainProducts;
    }
}
