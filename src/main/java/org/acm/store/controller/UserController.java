package org.acm.store.controller;


import org.acm.store.controller.validation.Authentication;
import org.acm.store.controller.validation.CustomException;
import org.acm.store.controller.validation.Validation;
import org.acm.store.model.DataBase;
import org.acm.store.model.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;


@Validated
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    /*@GetMapping
    public ArrayList<User> getUsers() {
//        if (!Authentication.isLogin(request))
//            throw new CustomException("please login first");
//        if (!Authentication.isAdmin(Authentication.loggedInUser(request)))
//            throw new CustomException("You dont have permission");
        return DataBase.getInstance().getUsers();
    }


    @GetMapping("/{id}")
    public User findUserById(@PathVariable("id") long id, HttpServletRequest request) {
        if (!Authentication.isLogin(request))
            throw new CustomException("please login first");
        if (!Authentication.isAdmin(Authentication.loggedInUser(request)))
            throw new CustomException("You dont have permission");
        return DataBase.getInstance().findUser(id);
    }*/

    /*@PostMapping("/profile")
    public User seeProfile(@RequestParam(required = false) @NotBlank @Valid String password,
                           @RequestParam(required = false) @NotBlank @Valid String email) {
//        if (!Authentication.isLogin(request))
//            throw new CustomException("please login first");
        //we must not show password --> html
        return DataBase.getInstance().validateUser(email, password);
    }

    @PostMapping("/profile/edit")
    public String editProfile(@RequestParam(required = false) @NotBlank @Valid String firstName,
                              @RequestParam(required = false) @NotBlank @Valid String lastName,
                              @RequestParam(required = false) @NotBlank @Valid String password,
                              @RequestParam(required = false) @NotBlank @Valid String email,
                              @RequestParam(required = false) @NotBlank @Valid String newEmail,
                              @RequestParam(required = false) @NotBlank @Valid String phoneNumber,
                              @RequestParam(required = false) @NotBlank @Valid String address) {
//        if (!Authentication.isLogin(request))
//            throw new CustomException("please login first");
        //we must not show password --> html
        DataBase dataBase = DataBase.getInstance();
        User user = Authentication.loggedInUser(email, password);
        if (Validation.isTaken(newEmail, phoneNumber))
            throw new CustomException("This email or phone-number is taken");
        dataBase.editUser(user.getID(), firstName, lastName, newEmail, phoneNumber, address);
        return "Edited successfully";
    }*/

    /*@PostMapping("/profile/password")
    public String changePassword(@RequestParam(required = false) @NotBlank @Valid String newPassword,
                                @RequestParam(required = false) @NotBlank @Valid String email,
                                @RequestParam(required = false) @NotBlank @Valid String password) {
//        if (!Authentication.isLogin(request))
//            throw new CustomException("please login first");
        //we must not show password --> html
        DataBase dataBase = DataBase.getInstance();
        User user = Authentication.loggedInUser(email, password);
        dataBase.changePassword(user.getID(),newPassword);
        return "Changed successfully";
    }*/
}