package org.acm.store.controller.util;

import org.acm.store.model.user.User;
import org.acm.store.model.user.admin.Admin;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

public class Authentication {

    public static boolean isAdmin(User user) {
        return user instanceof Admin;
    }

    /*public static void login(HttpServletResponse response, String email, String password) {
        Cookie emailCookie = new Cookie("email", email);
        Cookie passwordCookie = new Cookie("password", password);
        response.addCookie(emailCookie);
        response.addCookie(passwordCookie);
    }*/

    /*public static void logout(HttpServletResponse response) {
        Cookie emailCookie = new Cookie("email", null);
        emailCookie.setMaxAge(0);
        emailCookie.setSecure(true);
        emailCookie.setHttpOnly(true);
        emailCookie.setPath("/");

        Cookie passwordCookie = new Cookie("password", null);
        passwordCookie.setMaxAge(0);
        passwordCookie.setSecure(true);
        passwordCookie.setHttpOnly(true);
        passwordCookie.setPath("/");

        response.addCookie(emailCookie);
        response.addCookie(passwordCookie);
    }*/

    public static boolean isLogin(HttpServletRequest request) {
        Cookie[] cookie = request.getCookies();
        if (cookie != null) {
            Cookie email = null, password = null;
            Optional<Cookie> optionalEmail = Arrays.stream(cookie)
                    .filter(x -> "email".equals(x.getName()))
                    .findFirst();
            Optional<Cookie> optionalPassword = Arrays.stream(cookie)
                    .filter(x -> "password".equals(x.getName()))
                    .findFirst();
            if (optionalEmail.isPresent()) email = optionalEmail.get();
            if (optionalPassword.isPresent()) password = optionalPassword.get();
            return (email != null) && (password != null);
        }
        return false;
    }

    /*public static User loggedInUser(HttpServletRequest request) {
        Cookie[] cookie = request.getCookies();
        String email, password;
        if (cookie[0].getName().equals("email")) {
            email = cookie[0].getValue();
            password = cookie[1].getValue();
        } else {
            email = cookie[1].getValue();
            password = cookie[0].getValue();
        }
        DataBase dataBase = DataBase.getInstance();
        return dataBase.findUser(dataBase.validateUserByID(email, password));
    }*/

    /*public static User loggedInUser(String email, String password) {
        User user;
        DataBase dataBase = DataBase.getInstance();
        try {
            user = dataBase.findUser(dataBase.validateUserByID(email, password));
        }catch (CustomException exception){
            user = null;
        }
        return user;
    }*/
}
