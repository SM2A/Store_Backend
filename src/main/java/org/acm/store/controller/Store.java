package org.acm.store.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.acm.store.model.Costumer;
import org.acm.store.model.DataBase;
import org.acm.store.model.User;
import org.springframework.web.bind.annotation.*;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/")
public class Store {

    @GetMapping("/")
    public String homePage() {
        DataBase.getInstance().addAdmin("admin","admin","admin","admin","007","admin");
        return "home page";
    }

    /*@GetMapping("/signup")
    @ResponseBody
    public ModelAndView signupPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test.html");
        return modelAndView;
    }*/

    @GetMapping("/signup")
    public String signupPage(HttpServletRequest request) {
        if (Authentication.isLogin(request)) return "please logout first";
        return "signup page";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String password,
                         @RequestParam String email, @RequestParam String phoneNumber, @RequestParam String address,
                         HttpServletResponse response, HttpServletRequest request) {
        if (Authentication.isLogin(request)) return "please logout first";
        DataBase dataBase = DataBase.getInstance();
        if (Validation.isTaken(email, phoneNumber)) return "This email or phone-number is taken";
        dataBase.addCostumer(firstName, lastName, password, email, phoneNumber, address);
        Authentication.login(response, email, password);
        return String.valueOf(dataBase.validateUser(email, password));
    }

    @GetMapping("/login")
    public String loginPage(HttpServletRequest request) {
        if (Authentication.isLogin(request)) return "please logout first";
        return "login page";
    }

    @PostMapping("/login")
    public String login(@RequestParam String password, @RequestParam String email, HttpServletResponse response,
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

    @GetMapping("/addAdmin")
    public String addAdminPage(HttpServletRequest request) {
        if (!Authentication.isLogin(request)) return "please login first";
        if (!Authentication.isAdmin(Authentication.loggedInUser(request))) return "You dont have permission";
        return "add admin page";
    }

    @PostMapping("/addAdmin")
    public String addAdmin(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String password,
                           @RequestParam String email, @RequestParam String phoneNumber, @RequestParam String address,
                           HttpServletRequest request) {
        if (!Authentication.isLogin(request)) return "please login first";
        if (!Authentication.isAdmin(Authentication.loggedInUser(request))) return "You dont have permission";
        if (Validation.isTaken(email, phoneNumber)) return "This email or phone-number is taken";
        DataBase dataBase = DataBase.getInstance();
        dataBase.addAdmin(firstName, lastName, password, email, phoneNumber, address);
        return String.valueOf(dataBase.validateUser(email, password));
    }

    @PostMapping("purchase")
    public void purchase(@RequestBody ObjectNode json){
        User user = DataBase.getInstance().validateUser(json.get("email").asText(), json.get("password").asText());
        DataBase.getInstance().purchase(user.getID());
    }

    @PostMapping("/credit/add")
    public void addCredit(@RequestBody ObjectNode json){
        User user = DataBase.getInstance().validateUser(json.get("email").asText(), json.get("password").asText());
        //if(user instanceof Costumer)--->exception handling
        DataBase.getInstance().addCredit(user.getID(),json.get("amount").asLong());
    }

}