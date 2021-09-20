package org.acm.store.model;

import java.util.*;
import org.acm.store.controller.util.CustomException;
import org.acm.store.model.cart.*;
import org.acm.store.model.category.Category;
import org.acm.store.model.category.CategoryService;
import org.acm.store.model.comment.Comment;
import org.acm.store.model.comment.CommentService;
import org.acm.store.model.product.Product;
import org.acm.store.model.product.ProductService;
import org.acm.store.model.user.admin.Admin;
import org.acm.store.model.user.admin.AdminService;
import org.acm.store.model.user.customer.Costumer;
import org.acm.store.model.user.customer.CustomerService;
import org.hibernate.Session;
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
    ItemService itemService;


    /*private static DataBase instance;

    private DataBase() {}

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }*/

    public DataBase() {}

    /*public boolean isTaken(String email, String phoneNumber) {
        Session session = repo.getSessionFactory().openSession();
        session.beginTransaction();
        Costumer costumer = session.createNamedQuery(Costumer.GET_CUSTOMER_BY_EMAIL_PHONENUMBER,Costumer.class)
                .setParameter("email", email)
                .setParameter("phonenumber", phoneNumber)
                .uniqueResult();
        Admin admin = session.createNamedQuery("GET_ADMIN_BY_EMAIL_PHONENUMBER",Admin.class)
                .setParameter("email", email)
                .setParameter("phonenumber", phoneNumber)
                .uniqueResult();
        session.close();
        return costumer != null || admin != null;
    }*/

    public void addCostumer(String firstName, String lastName, String password,
                            String email, String phoneNumber, String address) {
        createCart(customerService.addCustomer(new Costumer(firstName,lastName,password,email,phoneNumber,address)));
    }

    public void addAdmin(String firstName, String lastName, String password,
                         String email, String phoneNumber, String address) {
        adminService.addAdmin(new Admin(firstName, lastName, password, email, phoneNumber, address));
    }

    /*public void editUser(long id, String firstName, String lastName, String email,
                         String phoneNumber, String address) {
        User user = users.get(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);
    }*/

    /*public void changePassword(long id, String password) {
        users.get(id).setPassword(password);
    }*/

    /*public Comment findCommentByID(long id) {
        return comments.get(id);
    }*/

    /*public ArrayList<Comment> getAllComments() {
        return new ArrayList<>(comments.values());
    }*/

    /*public long validateUserByID(String email, String password) {
        for (Map.Entry<Long, User> user : users.entrySet()) {
            if ((user.getValue().getEmail().equals(email)) && (user.getValue().getPassword().equals(password))) {
                return user.getKey();
            }
        }
        return -1;
    }

    public User validateUser(String email, String password) {
        for (Map.Entry<Long, User> user : users.entrySet()) {
            if ((user.getValue().getEmail().equals(email)) && (user.getValue().getPassword().equals(password))) {
                return user.getValue();
            }
        }
        return null;
    }

    public User findUser(long ID) {
        if (!users.containsKey(ID))
            throw new CustomException("User not found.");
        return users.get(ID);
    }*/

    /*public HashMap<Long, Cart> getUserCarts(long userID) {
        HashMap<Long, Cart> cartHashMap = new HashMap<>();
        for (Map.Entry<Long, Cart> entry : carts.entrySet()) {
            if (entry.getValue().getUserID() == userID) cartHashMap.put(entry.getKey(), entry.getValue());
        }
        return cartHashMap;
    }

    public ArrayList<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    public ArrayList<Cart> showUserCarts(long userID) {
        HashMap<Long, Cart> cartHashMap = new HashMap<>();
        for (Map.Entry<Long, Cart> entry : carts.entrySet()) {
            if (entry.getValue().getUserID() == userID) cartHashMap.put(entry.getKey(), entry.getValue());
        }
        return new ArrayList<>(cartHashMap.values());
    }*/

    public List<Product> getProducts() {
        return productService.getAllProduct();
    }

    /*public HashMap<Long, Comment> getUserComments(long userID) {
        HashMap<Long, Comment> commentHashMap = new HashMap<>();
        for (Map.Entry<Long, Comment> entry : comments.entrySet()) {
            if (entry.getValue().getUserID() == userID) commentHashMap.put(entry.getKey(), entry.getValue());
        }
        return commentHashMap;
    }

    public void editProduct(long id, String title, String description, int quantityAvailable,
                            int price, String category, String imgAddress) {
        Product product = products.get(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setQuantityAvailable(quantityAvailable);
        product.setPrice(price);
        product.setCategory(category);
        product.setImgAddress(imgAddress);
    }*/

    /*public ArrayList<Comment> getProductComments(long productId) {
        ArrayList<Comment> userComments = new ArrayList<>();
        for (Map.Entry<Long, Comment> entry : comments.entrySet()) {
            if (entry.getValue().getProductID() == productId) userComments.add(entry.getValue());
        }
        return userComments;
    }*/

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

    /*public void purchase(long userId) {
        Costumer user = (Costumer) users.get(userId);
        Cart cart = null;
        for (Map.Entry<Long, Cart> entry : getUserCarts(userId).entrySet()) {
            if (entry.getValue().getStatus() == Status.OPEN) cart = entry.getValue();
        }
        if (!user.hasEnoughCredit(cartPrice(cart.getID()))) {
            throw new CustomException("Not enough credit");
        }
        for (Map.Entry<Product, Integer> entry : getCartItems(cart.getID()).entrySet()) {
            //todo maybe quantities changed
            entry.getKey().setQuantityAvailable(entry.getKey().getQuantityAvailable() - entry.getValue());
        }
        user.purchase(cartPrice(cart.getID()));
        cart.purchase();
        cart.setStatus(Status.CLOSED);
        createCart(user.getID());
    }*/

    public void addProduct(String title, String description, int quantityAvailable,
                           int price, String category, String imgAddress) {

        if (getExistedProduct(title, category) != null) {
            Product product = getExistedProduct(title, category);
            product.addToStock(quantityAvailable);
            productService.updateProduct(product);
        } else {
            if (!isCategoryAvailable(category)) throw new CustomException("Category not available");
            productService.addProduct(new Product(title, description, quantityAvailable, price, category, imgAddress));
        }

    }

    public Product findProduct(long ID) {
        return productService.getProduct(ID);
    }

    public void addComment(long userID, long productID, String text) {
        commentService.addComment(new Comment(userID,productID,text));
    }

    /*public Comment findComment(long ID) {
        return comments.get(ID);
    }

    public void likeComment(long ID) {
        comments.get(ID).like();
    }

    public void dislikeComment(long ID) {
        comments.get(ID).dislike();
    }

    public void deleteComment(long ID) {
        comments.remove(ID);
    }*/

    public void createCart(long userID) {
        cartService.addCart(new Cart(userID));
    }

    /*public ArrayList<Cart> getCarts() {
        return new ArrayList<>(carts.values());
    }*/

    public Cart findOpenCartByUser(long userId) {
        return cartService.getCart(userId,Status.OPEN).get(0);
    }

    /*public HashMap<Long, Integer> getItemsInOpenCart(long userId) {
        Cart cart = findOpenCartByUser(userId);
        if (items.get(cart.getID()) != null) {
            return items.get(cart.getID());
        }
        return null;
    }*/

    /*public Cart findCart(long ID) {
        return carts.get(ID);
    }*/

    /*public long cartPrice(long cartID) {
        Session session = repo.getSessionFactory().openSession();
        session.beginTransaction();
        long price = (long) session.createNamedQuery(Item.GET_CART_PRICE)
                .setParameter("cid", cartID)
                .uniqueResult();
        session.close();
        return price;
    }*/


    /*public void addItem(long cartID, long productID) {
        Session session = repo.getSessionFactory().openSession();
        try(session) {
            session.beginTransaction();
            if (findProduct(productID).getQuantityAvailable() <= 0)
                throw new CustomException("Not enough quantity");
            if (getCartItems(cartID).containsKey(findProduct(productID))) {
                increaseItem(productID, cartID);
            } else {
                session.save(new Item(cartID,productID,1));
                session.getTransaction().commit();
            }
        }
    }*/

    /*public void deleteItem(long productID, long cartID) {
        if (!items.get(cartID).containsKey(productID))
            throw new CustomException("Your cart doesn't contain item with id: " + productID);
        items.get(cartID).remove(productID);
    }*/

    /*public void increaseItem(long productID, long cartID) {
        Session session = repo.getSessionFactory().openSession();
        try (session) {
            session.beginTransaction();
            Item item = (Item) session.getNamedQuery(Item.GET_ITEM)
                    .setParameter("cid",cartID)
                    .setParameter("pid",productID)
                    .uniqueResult();
            if (findProduct(productID).getQuantityAvailable() == item.getCount())
                throw new CustomException("Not enough quantity");
            item.setCount(item.getCount()+1);
            session.update(item);
            session.getTransaction().commit();
        }
    }*/

    /*public void decreaseItem(long productID, long cartID) {
        if (!items.get(cartID).containsKey(productID))
            throw new CustomException("Your cart doesn't contain item with id: " + productID);
        if (items.get(cartID).get(productID) == 1) deleteItem(productID, cartID);
        else items.get(cartID).replace(productID, items.get(cartID).get(productID) - 1);
    }

    public void setQuantityToAnItem(long productID, long cartID, int quantity) {
        if (findProduct(productID).getQuantityAvailable() < quantity)
            throw new CustomException("Not enough quantity");
        if (getCartItems(cartID).containsKey(findProduct(productID)))
            items.get(cartID).replace(productID, quantity);
        else
            items.get(cartID).put(productID, quantity);
    }*/

    /*public HashMap<Product, Integer> getCartItems(long cartID) {
        Session session = repo.getSessionFactory().openSession();
        session.beginTransaction();
        List<Item> list = session.createQuery("FROM item i WHERE i.cartID = :cid", Item.class)
                .setParameter("cid",cartID)
                .getResultList();
        session.close();
        HashMap<Product, Integer> itemsHashMap = new HashMap<>();
        for (Item item : list) itemsHashMap.put(findProduct(item.getProductID()),item.getCount());
        return itemsHashMap;
    }*/

    public Product getExistedProduct(String title, String category) {
        return productService.getProduct(title,new Category(category));
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

    /*public ArrayList<String> getCategories() {
        return categories;
    }

    public ArrayList<Product> getProductsByCategory(String category) {
        ArrayList<Product> selectedProducts = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getCategory().equals(category)) {
                selectedProducts.add(product);
            }
        }
        return selectedProducts;
    }*/

    /*public ArrayList<Product> searchProductsByName(String name) {
        ArrayList<Product> searchedProducts = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getTitle().equalsIgnoreCase(name)) searchedProducts.add(product);
            else if (product.getTitle().contains(name)) searchedProducts.add(product);
            else if (Pattern.compile(Pattern.quote(name), Pattern.CASE_INSENSITIVE).matcher(product.getTitle())
                    .find()) searchedProducts.add(product);
            else if (product.getTitle().matches("(?i).*" + name + ".*")) searchedProducts.add(product);
        }
        return searchedProducts;
    }*/

    /*public ArrayList<Product> searchProductsByCategory(String category) {
        ArrayList<Product> searchedProducts = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getCategory().equalsIgnoreCase(category)) searchedProducts.add(product);
        }
        return searchedProducts;
    }*/

    /*public List<ArrayList<Product>> getMainProducts() {//Randomly get 4 products for 3 categories to show in  home page
        List<ArrayList<Product>> allMainProducts = new ArrayList<>();
        if (categories.size() < 3) throw new CustomException("Not Enough Categories!\ntry localhost:8080/test.");
        for (int j = 0; j < 3; j++) {
            ArrayList<Product> mainProductsInACategory = new ArrayList<>();
            ArrayList<Product> list = getProductsByCategory(categories.get(j));
            Collections.shuffle(list);
            if (list.size() < 4) throw new CustomException("Not Enough Categories!\ntry localhost:8080/test.");
            for (int i = 0; i < 4; i++) {
                mainProductsInACategory.add(list.get(i));
            }
            allMainProducts.add(mainProductsInACategory);
        }
        return allMainProducts;
    }*/
}
