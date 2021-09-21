package org.acm.store.controller;

import org.acm.store.model.DataBase;
import org.acm.store.model.cart.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

@Validated
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class Store {

    @Autowired
    DataBase dataBase;

    @GetMapping("/")
    public String/*List<ArrayList<Product>>*/ homePage() {
        dataBase.addAdmin("admin", "admin", "admin", "admin@admin.com", "007", "admin");
//        return DataBase.getInstance().getMainProducts();
        return "Home page";
    }

    @GetMapping("/test")
    public String/*List<ArrayList<Product>>*/ test() {

        dataBase.addCategory("KEYBOARDTWO");
        dataBase.addCategory("KEYBOARDTHREE");
        dataBase.addCategory("KEYBOARDFOUR");

        dataBase.addProduct("MX KKKeys 001", "Logitech", 5, 100, "KEYBOARDTWO",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");
        dataBase.addProduct("MX KKKeys 67", "Logitech", 5, 100, "KEYBOARDTWO",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");
        dataBase.addProduct("MX KKKeys 42", "Logitech", 5, 100, "KEYBOARDTWO",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");
        dataBase.addProduct("MX KKKeys 56", "Logitech", 5, 100, "KEYBOARDTWO",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");


        dataBase.addProduct("MX Keyss88", "Logitech", 5, 100, "KEYBOARDTHREE",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");
        dataBase.addProduct("MX Keysss 0987", "Logitech", 5, 100, "KEYBOARDTHREE",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");
        dataBase.addProduct("MX Keyss 45", "Logitech", 5, 100, "KEYBOARDTHREE",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");
        dataBase.addProduct("MX Keysss 2345 iui", "Logitech", 5, 100, "KEYBOARDTHREE",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");

        dataBase.addProduct("MMX Keys ytf", "Logitech", 5, 100, "KEYBOARDFOUR",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");
        dataBase.addProduct("MXXX Keys tyf", "Logitech", 5, 100, "KEYBOARDFOUR",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");
        dataBase.addProduct("MXX Keys 56 err", "Logitech", 5, 100, "KEYBOARDFOUR",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");
        dataBase.addProduct("MMX Keys fdzdx", "Logitech", 5, 100, "KEYBOARDFOUR",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");

//        return DataBase.getInstance().getMainProducts();
        return "OK";
    }

    /*@GetMapping("/signup")
    public String signupPage(HttpServletRequest request) {
        if (Authentication.isLogin(request)) throw new CustomException("please logout first");
        return "signup page";
    }*/

    @GetMapping("/test1")
    public String test1(){

        long c1 = dataBase.addCostumer("Amin","Atyabi","123","amin@amin.net","123654789","Tehran");
        long c2 = dataBase.addCostumer("Paria","Khoshtab","123","test@test.net","987456321","Tehran");
        long c3 = dataBase.addCostumer("Parnian","Fazel","123","salam@test.net","147852369","Tehran");

        dataBase.addCategory("game");
        dataBase.addCategory("mouse");
        dataBase.addCategory("keyboard");

        long p1 = dataBase.addProduct("MFSX", "Microsoft", 5, 60, "GAME",
                "https://i1.sndcdn.com/artworks-000538033689-8y3q0k-t500x500.jpg");
        long p2 = dataBase.addProduct("Death Adder", "Razer", 5, 30, "MOUSE",
                "https://hardwaremarket.net/wp-content/uploads/2021/03/razer-deathadder-essential-optical-gaming-mouse-white-1571981760387.jpg");
        long p3 = dataBase.addProduct("MX Keys", "Logitech", 5, 100, "KEYBOARD",
                "https://www.logitech.com/content/dam/logitech/en/products/keyboards/mx-keys/gallery/us-mx-keys-gallery-graphite-front.png");

        Cart cart = dataBase.findOpenCartByUser(c1);

        /*dataBase.addItem(cart.getID(), 13);
        dataBase.addItem(cart.getID(), 13);
        dataBase.addItem(cart.getID(), 13);
        dataBase.addItem(cart.getID(), 14);
        dataBase.addItem(cart.getID(), 14);
        dataBase.addItem(cart.getID(), 15);*/

        dataBase.addComment(c2,p1,"wowwww");
        dataBase.addComment(c3,p1,"greattttt!!!!");

        dataBase.addComment(c1,p2,"wowwww");
        dataBase.addComment(c2,p3,"greattttt!!!!");

        dataBase.addCredit(c1, 10000);
        dataBase.addCredit(c2, 10000);
        dataBase.addCredit(c3, 10000);

        dataBase.addCredit(2, 10000);
//        dataBase.purchase(2);

//        cart = dataBase.findOpenCartByUser(2);

        /*dataBase.addItem(cart.getID(), 1);
        dataBase.addItem(cart.getID(), 2);*/

        dataBase.addRatingToProduct(p1,5);
        dataBase.addRatingToProduct(p1,5);
        dataBase.addRatingToProduct(p1,2);
        dataBase.addRatingToProduct(p1,1);
        dataBase.addRatingToProduct(p1,3);

        dataBase.addRatingToProduct(p2,4);
        dataBase.addRatingToProduct(p2,5);
        dataBase.addRatingToProduct(p2,2);
        dataBase.addRatingToProduct(p2,1);
        dataBase.addRatingToProduct(p2,3);

        dataBase.addRatingToProduct(p3,1);
        dataBase.addRatingToProduct(p3,5);
        dataBase.addRatingToProduct(p3,2);
        dataBase.addRatingToProduct(p3,1);
        dataBase.addRatingToProduct(p3,3);

        return "Test 1";
    }

    /*@PostMapping("/signup")
    public String signup(@RequestParam(required = false) @NotBlank @Valid String firstName,
                         @RequestParam(required = false) @NotBlank @Valid String lastName,
                         @RequestParam(required = false) @NotBlank @Valid String password,
                         @RequestParam(required = false) @NotBlank @Valid String email,
                         @RequestParam(required = false) @NotBlank @Valid String phoneNumber,
                         @RequestParam(required = false) @NotBlank @Valid String address) throws JSONException {
        DataBase.getInstance() DataBase.getInstance() = DataBase.getInstance();
        if (Validation.isTaken(email, phoneNumber)) throw new CustomException("This email or phone-number is taken");
        DataBase.getInstance().addCostumer(firstName, lastName, password, email, phoneNumber, address);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 1);
        jsonObject.put("ID", DataBase.getInstance().validateUserByID(email, password));
        jsonObject.put("email", email);
        jsonObject.put("password", password);

        return jsonObject.toString();
    }*/

    /*@GetMapping("/login")
    public String loginPage(HttpServletRequest request) {
        if (Authentication.isLogin(request)) throw new CustomException("please logout first");
        return "login page";
    }*/

    /*@PostMapping("/login")
    public String login(@RequestParam(required = false) @NotBlank @Valid String password,
                        @RequestParam(required = false) @NotBlank @Valid String email) throws JSONException {
        DataBase.getInstance() DataBase.getInstance() = DataBase.getInstance();
        long ID = DataBase.getInstance().validateUserByID(email, password);
        if (ID != -1) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", 1);
            jsonObject.put("ID", DataBase.getInstance().validateUserByID(email, password));
            jsonObject.put("email", email);
            jsonObject.put("password", password);
            return jsonObject.toString();
        } else throw new CustomException("email or password in correct");
    }*/

    /*@GetMapping("/logout")
    public String logout(HttpServletResponse response, HttpServletRequest request) {
        if (!Authentication.isLogin(request)) throw new CustomException("please login first");
        User user = Authentication.loggedInUser(request);
        Authentication.logout(response);
        return "goodbye " + user.getFirstName();
    }*/

    /*@PostMapping("/valid_login")
    public String validLogin(@RequestParam(required = false) @NotBlank @Valid String password,
                             @RequestParam(required = false) @NotBlank @Valid String email) {
        if (Authentication.loggedInUser(email, password) != null) return "1";
        else return "0";
    }*/

    /*@PostMapping("/valid_admin")
    public String validAdmin(@RequestParam(required = false) @NotBlank @Valid String password,
                             @RequestParam(required = false) @NotBlank @Valid String email) {
        User user = Authentication.loggedInUser(email, password);
        if (Authentication.isAdmin(user)) return "1";
        else return "0";
    }*/

    /*@GetMapping("/add/admin")
    public String addAdminPage(HttpServletRequest request) {
        if (!Authentication.isLogin(request)) throw new CustomException("please login first");
        if (!Authentication.isAdmin(Authentication.loggedInUser(request)))
            throw new CustomException("You dont have permission");
        return "add admin page";
    }*/

    /*@PostMapping("/admin/add")
    public String addAdmin(@RequestParam(required = false) @NotBlank @Valid String firstName,
                           @RequestParam(required = false) @NotBlank @Valid String lastName,
                           @RequestParam(required = false) @NotBlank @Valid String password,
                           @RequestParam(required = false) @NotBlank @Valid String email,
                           @RequestParam(required = false) @NotBlank @Valid String phoneNumber,
                           @RequestParam(required = false) @NotBlank @Valid String address) {
//        if (!Authentication.isLogin(request)) throw new CustomException("please login first");
//        if (!Authentication.isAdmin(Authentication.loggedInUser(request)))
//            throw new CustomException("You dont have permission");
        if (Validation.isTaken(email, phoneNumber)) throw new CustomException("This email or phone-number is taken");
        DataBase.getInstance() DataBase.getInstance() = DataBase.getInstance();
        DataBase.getInstance().addAdmin(firstName, lastName, password, email, phoneNumber, address);
        return String.valueOf(DataBase.getInstance().validateUserByID(email, password));
    }*/

    /*@PostMapping("/purchase")
    public String purchase(@RequestParam(required = false) @NotBlank @Valid String password,
                           @RequestParam(required = false) @NotBlank @Valid String email) {
//        if (!Authentication.isLogin(request)) throw new CustomException("Please login first");
//        if (Authentication.isAdmin(Authentication.loggedInUser(request)))
//            throw new CustomException("Make sure you are a costumer");
        User user = Authentication.loggedInUser(email,password);
        DataBase.getInstance().purchase(user.getID());
        return "Thank you for your order";
    }*/

    /*@PostMapping("/credit/add")
    public String addCredit(@RequestParam(required = false) @NotBlank @Valid String password,
                            @RequestParam(required = false) @NotBlank @Valid String email,
                            @RequestParam(required = false) @NotBlank @Valid String amount) {
//        if (!Authentication.isLogin(request)) throw new CustomException("Please login first");
//        if (Authentication.isAdmin(Authentication.loggedInUser(request)))
//            throw new CustomException("Make sure you are a costumer");
        User user = Authentication.loggedInUser(email, password);
        DataBase.getInstance().addCredit(user.getID(), Long.parseLong(amount));
        return "Successful payment";
    }*/
}
