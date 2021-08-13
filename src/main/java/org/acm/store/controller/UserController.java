package org.acm.store.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.acm.store.model.DataBase;
import org.acm.store.model.User;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public ArrayList<User> getUsers(@RequestBody ObjectNode json){
        User user = DataBase.getInstance().validateUser(json.get("email").asText(), json.get("password").asText());
        //if(user instanceof Admin)--->exception handling
        return DataBase.getInstance().getUsers();
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable("id") long id){
        return DataBase.getInstance().findUser(id);
    }

    @GetMapping("/profile")
    public User seeProfile(@RequestBody ObjectNode json){
        //we must not show password ---> front
        return DataBase.getInstance().validateUser(json.get("email").asText(), json.get("password").asText());
    }

}
