package org.acm.store.controller;


import org.acm.store.controller.validation.Authentication;
import org.acm.store.controller.validation.CustomException;
import org.acm.store.model.DataBase;
import org.acm.store.model.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


@Validated
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public ArrayList<User> getUsers(HttpServletRequest request) {
        if (!Authentication.isLogin(request))
            throw new CustomException("please login first");
        if (!Authentication.isAdmin(Authentication.loggedInUser(request)))
            throw new CustomException("You dont have permission");
        return DataBase.getInstance().getUsers();
    }


    @GetMapping("/{id}")
    public User findUserById(@PathVariable("id") long id, HttpServletRequest request) {
        if (!Authentication.isLogin(request))
            throw new CustomException("please login first");
        if (!Authentication.isAdmin(Authentication.loggedInUser(request)))
            throw new CustomException("You dont have permission");
        return DataBase.getInstance().findUser(id);
    }

    @GetMapping("/profile")
    public User seeProfile(HttpServletRequest request) {
        if (!Authentication.isLogin(request))
            throw new CustomException("please login first");
        //we must not show password --> html
        return Authentication.loggedInUser(request);
    }
}
