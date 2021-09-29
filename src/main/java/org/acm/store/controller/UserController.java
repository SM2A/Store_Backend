package org.acm.store.controller;

import org.acm.store.controller.util.Authentication;
import org.acm.store.model.DataBase;
import org.acm.store.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Validated
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    DataBase dataBase;

    @Autowired
    Authentication authentication;

    @GetMapping
    public List<User> getUsers() {
        return dataBase.getUsers();
    }

    /*@GetMapping("/{id}")
    public User findUserById(@PathVariable("id") long id, HttpServletRequest request) {
        if (!Authentication.isLogin(request))
            throw new CustomException("please login first");
        if (!Authentication.isAdmin(Authentication.loggedInUser(request)))
            throw new CustomException("You dont have permission");
        return DataBase.getInstance().findUser(id);
    }*/

    @PostMapping("/profile")
    public User seeProfile(@RequestParam(required = false) @NotBlank @Valid String password,
                           @RequestParam(required = false) @NotBlank @Valid String email) {

        return dataBase.validateUser(email, password);
    }

    @PostMapping("/profile/edit")
    public String editProfile(@RequestParam(required = false) @NotBlank @Valid String firstName,
                              @RequestParam(required = false) @NotBlank @Valid String lastName,
                              @RequestParam(required = false) @NotBlank @Valid String password,
                              @RequestParam(required = false) @NotBlank @Valid String email,
                              @RequestParam(required = false) @NotBlank @Valid String newEmail,
                              @RequestParam(required = false) @NotBlank @Valid String phoneNumber,
                              @RequestParam(required = false) @NotBlank @Valid String address) {

        User user = authentication.loggedInUser(email, password);
        dataBase.editUser(user.getID(), firstName, lastName, newEmail, phoneNumber, address);
        return "Edited successfully";
    }

    @PostMapping("/profile/password")
    public String changePassword(@RequestParam(required = false) @NotBlank @Valid String newPassword,
                                 @RequestParam(required = false) @NotBlank @Valid String email,
                                 @RequestParam(required = false) @NotBlank @Valid String password) {

        User user = authentication.loggedInUser(email, password);
        dataBase.changePassword(user.getID(), newPassword);
        return "Changed successfully";
    }
}