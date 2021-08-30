package org.acm.store.controller;

import org.acm.store.controller.validation.Authentication;
import org.acm.store.controller.validation.CustomException;
import org.acm.store.controller.validation.Validation;
import org.acm.store.model.Cart;
import org.acm.store.model.DataBase;
import org.acm.store.model.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */


@Validated
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class Store {

    @GetMapping("/")
    public String homePage() {
        if (!Validation.isTaken("admin@admin.com", "007"))
            DataBase.getInstance().addAdmin("admin", "admin", "admin", "admin@admin.com", "007", "admin");
        return "home page";
    }

    /*@GetMapping("/signup")
    public String signupPage(HttpServletRequest request) {
        if (Authentication.isLogin(request)) throw new CustomException("please logout first");
        return "signup page";
    }*/

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

        /*DataBase.getInstance().addCategory("game");
        DataBase.getInstance().addCategory("mouse");
        DataBase.getInstance().addCategory("keyboard");

        DataBase.getInstance().addProduct("MFSX", "Microsoft", 5, 60, "GAME");
        DataBase.getInstance().addProduct("Death Adder", "Razer", 5, 30, "MOUSE");
        DataBase.getInstance().addProduct("MX Keys", "Logitech", 5, 100, "KEYBOARD");

        Cart cart = DataBase.getInstance().findOpenCartByUser(2);
        DataBase.getInstance().addItem(cart.getID(), 1);
        DataBase.getInstance().addItem(cart.getID(), 1);
        DataBase.getInstance().addItem(cart.getID(), 1);
        DataBase.getInstance().addItem(cart.getID(), 2);
        DataBase.getInstance().addItem(cart.getID(), 2);
        DataBase.getInstance().addItem(cart.getID(), 3);*/

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
    public String purchase(HttpServletRequest request) {
        if (!Authentication.isLogin(request)) throw new CustomException("Please login first");
        if (Authentication.isAdmin(Authentication.loggedInUser(request)))
            throw new CustomException("Make sure you are a costumer");
        User user = Authentication.loggedInUser(request);
        DataBase.getInstance().purchase(user.getID());
        return "Thank you for your order";
    }

    @PostMapping("/credit/add")
    public String addCredit(@RequestParam(required = false) @NotBlank @Valid String amount,
                            HttpServletRequest request) {
        if (!Authentication.isLogin(request)) throw new CustomException("Please login first");
        if (Authentication.isAdmin(Authentication.loggedInUser(request)))
            throw new CustomException("Make sure you are a costumer");
        User user = Authentication.loggedInUser(request);
        DataBase.getInstance().addCredit(user.getID(), Long.parseLong(amount));
        return "Successful payment";
    }
}
