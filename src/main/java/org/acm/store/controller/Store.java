package org.acm.store.controller;

import org.acm.store.controller.validation.Authentication;
import org.acm.store.controller.validation.CustomException;
import org.acm.store.controller.validation.Validation;
import org.acm.store.model.Cart;
import org.acm.store.model.DataBase;
import org.acm.store.model.Product;
import org.acm.store.model.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */


@Validated
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class Store {

    @GetMapping("/")
    public /*String*/List<ArrayList<Product>> homePage() {
        if (!Validation.isTaken("admin@admin.com", "007"))
            DataBase.getInstance().addAdmin("admin", "admin", "admin", "admin@admin.com", "007", "admin");
        return DataBase.getInstance().getMainProducts();
        //return "Home page";
    }

    @GetMapping("/test")
    public List<ArrayList<Product>> test() {

        DataBase.getInstance().addCategory("KEYBOARDTWO");
        DataBase.getInstance().addCategory("KEYBOARDTHREE");
        DataBase.getInstance().addCategory("KEYBOARDFOUR");

        DataBase.getInstance().addProduct("MX KKKeys 001", "Logitech", 5, 100, "KEYBOARDTWO",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");
        DataBase.getInstance().addProduct("MX KKKeys 67", "Logitech", 5, 100, "KEYBOARDTWO",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");
        DataBase.getInstance().addProduct("MX KKKeys 42", "Logitech", 5, 100, "KEYBOARDTWO",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");
        DataBase.getInstance().addProduct("MX KKKeys 56", "Logitech", 5, 100, "KEYBOARDTWO",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");


        DataBase.getInstance().addProduct("MX Keyss88", "Logitech", 5, 100, "KEYBOARDTHREE",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");
        DataBase.getInstance().addProduct("MX Keysss 0987", "Logitech", 5, 100, "KEYBOARDTHREE",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");
        DataBase.getInstance().addProduct("MX Keyss 45", "Logitech", 5, 100, "KEYBOARDTHREE",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");
        DataBase.getInstance().addProduct("MX Keysss 2345 iui", "Logitech", 5, 100, "KEYBOARDTHREE",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");

        DataBase.getInstance().addProduct("MMX Keys ytf", "Logitech", 5, 100, "KEYBOARDFOUR",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");
        DataBase.getInstance().addProduct("MXXX Keys tyf", "Logitech", 5, 100, "KEYBOARDFOUR",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");
        DataBase.getInstance().addProduct("MXX Keys 56 err", "Logitech", 5, 100, "KEYBOARDFOUR",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");
        DataBase.getInstance().addProduct("MMX Keys fdzdx", "Logitech", 5, 100, "KEYBOARDFOUR",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");

        return DataBase.getInstance().getMainProducts();
    }

    /*@GetMapping("/signup")
    public String signupPage(HttpServletRequest request) {
        if (Authentication.isLogin(request)) throw new CustomException("please logout first");
        return "signup page";
    }*/

    @GetMapping("/test1")
    public String test1(){

        DataBase.getInstance().addCostumer("Amin","Atyabi","123","amin@amin.net","123654789","Tehran");
        DataBase.getInstance().addCostumer("Paria","Khoshtab","123","test@test.net","987456321","Tehran");
        DataBase.getInstance().addCostumer("Parnian","Fazel","123","salam@test.net","147852369","Tehran");

        DataBase.getInstance().addCategory("game");
        DataBase.getInstance().addCategory("mouse");
        DataBase.getInstance().addCategory("keyboard");

        DataBase.getInstance().addProduct("MFSX", "Microsoft", 5, 60, "GAME",
                "https://i1.sndcdn.com/artworks-000538033689-8y3q0k-t500x500.jpg");
        DataBase.getInstance().addProduct("Death Adder", "Razer", 5, 30, "MOUSE",
                "https://hardwaremarket.net/wp-content/uploads/2021/03/razer-deathadder-essential-optical-gaming-mouse-white-1571981760387.jpg");
        DataBase.getInstance().addProduct("MX Keys", "Logitech", 5, 100, "KEYBOARD",
                "https://www.logitech.com/content/dam/logitech/en/products/keyboards/mx-keys/gallery/us-mx-keys-gallery-graphite-front.png");

        Cart cart = DataBase.getInstance().findOpenCartByUser(2);

        DataBase.getInstance().addItem(cart.getID(), 1);
        DataBase.getInstance().addItem(cart.getID(), 1);
        DataBase.getInstance().addItem(cart.getID(), 1);
        DataBase.getInstance().addItem(cart.getID(), 2);
        DataBase.getInstance().addItem(cart.getID(), 2);
        DataBase.getInstance().addItem(cart.getID(), 3);

        DataBase.getInstance().addComment(3,1,"wowwww");
        DataBase.getInstance().addComment(4,1,"greattttt!!!!");

        DataBase.getInstance().addComment(5,2,"wowwww");
        DataBase.getInstance().addComment(2,3,"greattttt!!!!");

        DataBase.getInstance().addCredit(3, 10000);
        DataBase.getInstance().addCredit(4, 10000);
        DataBase.getInstance().addCredit(5, 10000);

        DataBase.getInstance().addCredit(2, 10000);
        DataBase.getInstance().purchase(2);

        cart = DataBase.getInstance().findOpenCartByUser(2);

        DataBase.getInstance().addItem(cart.getID(), 1);
        DataBase.getInstance().addItem(cart.getID(), 2);

        return "Test 1";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam(required = false) @NotBlank @Valid String firstName,
                         @RequestParam(required = false) @NotBlank @Valid String lastName,
                         @RequestParam(required = false) @NotBlank @Valid String password,
                         @RequestParam(required = false) @NotBlank @Valid String email,
                         @RequestParam(required = false) @NotBlank @Valid String phoneNumber,
                         @RequestParam(required = false) @NotBlank @Valid String address) throws JSONException {
        DataBase dataBase = DataBase.getInstance();
        if (Validation.isTaken(email, phoneNumber)) throw new CustomException("This email or phone-number is taken");
        dataBase.addCostumer(firstName, lastName, password, email, phoneNumber, address);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 1);
        jsonObject.put("ID", dataBase.validateUserByID(email, password));
        jsonObject.put("email", email);
        jsonObject.put("password", password);

        return jsonObject.toString();
    }

    /*@GetMapping("/login")
    public String loginPage(HttpServletRequest request) {
        if (Authentication.isLogin(request)) throw new CustomException("please logout first");
        return "login page";
    }*/

    @PostMapping("/login")
    public String login(@RequestParam(required = false) @NotBlank @Valid String password,
                        @RequestParam(required = false) @NotBlank @Valid String email) throws JSONException {
        DataBase dataBase = DataBase.getInstance();
        long ID = dataBase.validateUserByID(email, password);
        if (ID != -1) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", 1);
            jsonObject.put("ID", dataBase.validateUserByID(email, password));
            jsonObject.put("email", email);
            jsonObject.put("password", password);
            return jsonObject.toString();
        } else throw new CustomException("email or password in correct");
    }

    /*@GetMapping("/logout")
    public String logout(HttpServletResponse response, HttpServletRequest request) {
        if (!Authentication.isLogin(request)) throw new CustomException("please login first");
        User user = Authentication.loggedInUser(request);
        Authentication.logout(response);
        return "goodbye " + user.getFirstName();
    }*/

    @PostMapping("/valid_login")
    public String validLogin(@RequestParam(required = false) @NotBlank @Valid String password,
                             @RequestParam(required = false) @NotBlank @Valid String email) {
        if (Authentication.loggedInUser(email, password) != null) return "1";
        else return "0";
    }

    @PostMapping("/valid_admin")
    public String validAdmin(@RequestParam(required = false) @NotBlank @Valid String password,
                             @RequestParam(required = false) @NotBlank @Valid String email) {
        User user = Authentication.loggedInUser(email, password);
        if (Authentication.isAdmin(user)) return "1";
        else return "0";
    }

    /*@GetMapping("/add/admin")
    public String addAdminPage(HttpServletRequest request) {
        if (!Authentication.isLogin(request)) throw new CustomException("please login first");
        if (!Authentication.isAdmin(Authentication.loggedInUser(request)))
            throw new CustomException("You dont have permission");
        return "add admin page";
    }*/

    @PostMapping("/admin/add")
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
        DataBase dataBase = DataBase.getInstance();
        dataBase.addAdmin(firstName, lastName, password, email, phoneNumber, address);
        return String.valueOf(dataBase.validateUserByID(email, password));
    }

    @PostMapping("/purchase")
    public String purchase(@RequestParam(required = false) @NotBlank @Valid String password,
                           @RequestParam(required = false) @NotBlank @Valid String email) {
//        if (!Authentication.isLogin(request)) throw new CustomException("Please login first");
//        if (Authentication.isAdmin(Authentication.loggedInUser(request)))
//            throw new CustomException("Make sure you are a costumer");
        User user = Authentication.loggedInUser(email,password);
        DataBase.getInstance().purchase(user.getID());
        return "Thank you for your order";
    }

    @PostMapping("/credit/add")
    public String addCredit(@RequestParam(required = false) @NotBlank @Valid String password,
                            @RequestParam(required = false) @NotBlank @Valid String email,
                            @RequestParam(required = false) @NotBlank @Valid String amount) {
//        if (!Authentication.isLogin(request)) throw new CustomException("Please login first");
//        if (Authentication.isAdmin(Authentication.loggedInUser(request)))
//            throw new CustomException("Make sure you are a costumer");
        User user = Authentication.loggedInUser(email, password);
        DataBase.getInstance().addCredit(user.getID(), Long.parseLong(amount));
        return "Successful payment";
    }
}
