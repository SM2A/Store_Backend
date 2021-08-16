package org.acm.store.controller;

import org.acm.store.controller.validation.Authentication;
import org.acm.store.controller.validation.Validation;
import org.acm.store.model.DataBase;
import org.acm.store.model.User;
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
public class Store {

    @GetMapping("/")
    public String homePage() {
        DataBase.getInstance().addAdmin("admin", "admin", "admin", "admin", "007", "admin");
        return "home page";
    }

    @GetMapping("/signup")
    public String signupPage(HttpServletRequest request) {
        if (Authentication.isLogin(request)) return "please logout first";
        return "signup page";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam(required = false) @NotBlank @Valid String firstName,
                         @RequestParam(required = false) @NotBlank @Valid String lastName,
                         @RequestParam(required = false) @NotBlank @Valid String password,
                         @RequestParam(required = false) @NotBlank @Valid String email,
                         @RequestParam(required = false) @NotBlank @Valid String phoneNumber,
                         @RequestParam(required = false) @NotBlank @Valid String address,
                         HttpServletResponse response, HttpServletRequest request){
        if (Authentication.isLogin(request)) return "please logout first";
        DataBase dataBase = DataBase.getInstance();
        if (Validation.isTaken(email, phoneNumber)) return "This email or phone-number is taken";
        dataBase.addCostumer(firstName, lastName, password, email, phoneNumber, address);
        Authentication.login(response, email, password);
        return String.valueOf(dataBase.validateUserByID(email, password));
    }

    @GetMapping("/login")
    public String loginPage(HttpServletRequest request) {
        if (Authentication.isLogin(request)) return "please logout first";
        return "login page";
    }

    @PostMapping("/login")
    public String login(@RequestParam(required = false) @NotBlank @Valid String password,
                        @RequestParam(required = false) @NotBlank @Valid String email, HttpServletResponse response,
                        HttpServletRequest request) {
        if (Authentication.isLogin(request)) return "please logout first";
        long ID = DataBase.getInstance().validateUserByID(email, password);
        if (ID != -1) {
            Authentication.login(response, email, password);
            return "Welcome " + DataBase.getInstance().findUser(ID).getFirstName();
        } else return "email or password is incorrect";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response, HttpServletRequest request) {
        if (!Authentication.isLogin(request)) return "please login first";
        User user = Authentication.loggedInUser(request);
        Authentication.logout(response);
        return "goodbye " + user.getFirstName();
    }

    @GetMapping("/add/admin")
    public String addAdminPage(HttpServletRequest request) {
        if (!Authentication.isLogin(request)) return "please login first";
        if (!Authentication.isAdmin(Authentication.loggedInUser(request))) return "You dont have permission";
        return "add admin page";
    }

    @PostMapping("/add/admin")
    public String addAdmin(@RequestParam(required = false) @NotBlank @Valid String firstName,
                           @RequestParam(required = false) @NotBlank @Valid String lastName,
                           @RequestParam(required = false) @NotBlank @Valid String password,
                           @RequestParam(required = false) @NotBlank @Valid String email,
                           @RequestParam(required = false) @NotBlank @Valid String phoneNumber,
                           @RequestParam(required = false) @NotBlank @Valid String address,
                           HttpServletRequest request) {
        if (!Authentication.isLogin(request)) return "please login first";
        if (!Authentication.isAdmin(Authentication.loggedInUser(request))) return "You dont have permission";
        if (Validation.isTaken(email, phoneNumber)) return "This email or phone-number is taken";
        DataBase dataBase = DataBase.getInstance();
        dataBase.addAdmin(firstName, lastName, password, email, phoneNumber, address);
        return String.valueOf(dataBase.validateUserByID(email, password));
    }

    @PostMapping("purchase")
    public String purchase(HttpServletRequest request) {
        if (!Authentication.isLogin(request)) return "Please login first";
        if (Authentication.isAdmin(Authentication.loggedInUser(request))) return "Make sure you are a costumer";
        User user = Authentication.loggedInUser(request);
        DataBase.getInstance().purchase(user.getID());
        return "Thank you for your order";
    }

    @PostMapping("/credit/add")
    public String addCredit(@RequestParam(required = false) @NotBlank @Valid long amount,
                            HttpServletRequest request) {
        if (!Authentication.isLogin(request)) return "Please login first";
        if (Authentication.isAdmin(Authentication.loggedInUser(request))) return "Make sure you are a costumer";
        User user = Authentication.loggedInUser(request);
        DataBase.getInstance().addCredit(user.getID(), amount);
        return "Successful payment.";
    }
}
