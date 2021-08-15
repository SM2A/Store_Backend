package org.acm.store.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.acm.store.model.DataBase;
import org.acm.store.model.User;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String getUsers(HttpServletRequest request){
        if (!Authentication.isLogin(request)) return "please login first";
        if (!Authentication.isAdmin(Authentication.loggedInUser(request))) return "You dont have permission";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List list = Stream.of(DataBase.getInstance().getUsers()).collect(Collectors.toList());
        return gson.toJson(list);
    }

    //handle exception: if id doesn't exist
    @GetMapping("/{id}")
    public String findUserById(@PathVariable("id") long id, HttpServletRequest request){
        if (!Authentication.isLogin(request)) return "please login first";
        if (!Authentication.isAdmin(Authentication.loggedInUser(request))) return "You dont have permission";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        User user = DataBase.getInstance().findUser(id);
        List list = Stream.of(new User(user.getID(),user.getFirstName(),user.getLastName(),user.getPassword(),
                    user.getEmail(),user.getPhoneNumber(),user.getAddress())).collect(Collectors.toList());
        return gson.toJson(list);
    }

    @GetMapping("/profile")
    public String seeProfile(HttpServletRequest request){
        if (!Authentication.isLogin(request)) return "please login first";
        //we must not show password
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        User user = Authentication.loggedInUser(request);
        List list = Stream.of(new User(user.getID(),user.getFirstName(),user.getLastName(),user.getPassword(),
                    user.getEmail(),user.getPhoneNumber(),user.getAddress())).collect(Collectors.toList());
        return gson.toJson(list);
    }

}
