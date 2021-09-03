package org.acm.store.controller;

import org.acm.store.controller.validation.Authentication;
import org.acm.store.controller.validation.CustomException;
import org.acm.store.model.Comment;
import org.acm.store.model.DataBase;
import org.acm.store.model.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

@Validated
@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {

    @GetMapping(produces = "application/json")
    public String getComments() throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (Comment comment : DataBase.getInstance().getAllComments()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", comment.getID());
            String userName = DataBase.getInstance().findUser(comment.getUserID()).getFirstName() + " " +
                              DataBase.getInstance().findUser(comment.getUserID()).getLastName();
            jsonObject.put("user", userName);
            jsonObject.put("text", comment.getText());
            jsonObject.put("likes", comment.getLikes());
            jsonObject.put("dislikes", comment.getDislikes());
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }

    @PostMapping("/add")
    public String addComment(@RequestParam(required = false) @NotBlank @Valid String password,
                             @RequestParam(required = false) @NotBlank @Valid String email,
                             @RequestParam(required = false) @NotBlank @Valid String productID,
                             @RequestParam(required = false) @NotBlank @Valid String text) {
//        if (!Authentication.isLogin(request)) throw new CustomException("please login first");
//        if (Authentication.isAdmin(Authentication.loggedInUser(request)))
//            throw new CustomException("Make sure you are a costumer");
//        if (DataBase.getInstance().findProduct(Long.parseLong(productID)) == null)
//            throw new CustomException("Product Id Doesn't Exist");
        User user = Authentication.loggedInUser(email, password);
        DataBase.getInstance().addComment(user.getID(), Long.parseLong(productID), text);
        return "Your comment has been successfully added";
    }

    @GetMapping("/{productId}")
    public ArrayList<Comment> showProductComments(@PathVariable("productId") long productId) {
        if (DataBase.getInstance().findProduct(productId) == null)
            throw new CustomException("Product Id Doesn't Exist");
        return DataBase.getInstance().getProductComments(productId);
    }

    @PostMapping("/{id}/like")
    public String likeComment(@PathVariable("id") long id, HttpServletRequest request) {
        if (!Authentication.isLogin(request)) throw new CustomException("please login first");
        if (Authentication.isAdmin(Authentication.loggedInUser(request)))
            throw new CustomException("Make sure you are a costumer");
        if (DataBase.getInstance().findComment(id) == null) throw new CustomException("Comment Id Doesn't Exist");
        DataBase.getInstance().likeComment(id);
        return "successfully liked";
    }

    @PostMapping("/{id}/dislike")
    public String dislikeComment(@PathVariable("id") long id, HttpServletRequest request) {
        if (!Authentication.isLogin(request)) throw new CustomException("please login first");
        if (Authentication.isAdmin(Authentication.loggedInUser(request)))
            throw new CustomException("Make sure you are a costumer");
        if (DataBase.getInstance().findComment(id) == null) throw new CustomException("Comment Id Doesn't Exist.");
        DataBase.getInstance().dislikeComment(id);
        return "successfully disliked";
    }
}
