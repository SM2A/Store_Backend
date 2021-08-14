package org.acm.store.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.acm.store.model.Costumer;
import org.acm.store.model.DataBase;
import org.acm.store.model.User;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class Store {

    @GetMapping
    public String homePage() {
        return "home page";
    }

    @GetMapping("signup")
    public String signupPage() {
        return "signup page";
    }

    @PostMapping("signup")
    public void signup(@RequestBody ObjectNode json) {
        DataBase.getInstance().addCostumer(json.get("firstName").asText(),json.get("lastName").asText(),
                                            json.get("password").asText(), json.get("email").asText(),
                                            json.get("phoneNumber").asText(), json.get("address").asText());
    }

    @GetMapping("login")
    public String loginPage() {
        return "login page";
    }

    @PostMapping("login")
    public User login(@RequestBody ObjectNode json) {
        return DataBase.getInstance().validateUser(json.get("email").asText(), json.get("password").asText());
    }

    @GetMapping("logout")
    public String logout() {
        return "logout";
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