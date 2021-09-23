package org.acm.store.controller;

import org.acm.store.controller.util.Authentication;
import org.acm.store.controller.util.CustomException;
import org.acm.store.model.DataBase;
import org.acm.store.model.comment.Comment;
import org.acm.store.model.user.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Validated
@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {

    @Autowired
    DataBase dataBase;

    @Autowired
    Authentication authentication;

    @GetMapping(produces = "application/json")
    public String getComments() throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (Comment comment : dataBase.getAllComments()) {
            commentInfo(jsonArray, comment);
        }
        return jsonArray.toString();
    }

    private void commentInfo(JSONArray jsonArray, Comment comment) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", comment.getID());
        String userName = dataBase.findUser(comment.getUserID()).getFirstName() + " " +
                dataBase.findUser(comment.getUserID()).getLastName();
        jsonObject.put("user", userName);
        jsonObject.put("text", comment.getText());
        jsonObject.put("likes", comment.getLikes());
        jsonObject.put("dislikes", comment.getDislikes());
        jsonArray.put(jsonObject);
    }

    @PostMapping("/add")
    public String addComment(@RequestParam(required = false) @NotBlank @Valid String password,
                             @RequestParam(required = false) @NotBlank @Valid String email,
                             @RequestParam(required = false) @NotBlank @Valid String productID,
                             @RequestParam(required = false) @NotBlank @Valid String text) {

        User user = authentication.loggedInUser(email, password);
        dataBase.addComment(user.getID(), Long.parseLong(productID), text);
        return "Your comment has been successfully added";
    }

    @GetMapping("/{productId}")
    public String showProductComments(@PathVariable("productId") long productId) throws JSONException {
        if (dataBase.findProduct(productId) == null)
            throw new CustomException("Product Id Doesn't Exist");
        JSONArray jsonArray = new JSONArray();
        for (Comment comment : dataBase.getProductComments(productId)) {
            commentInfo(jsonArray, comment);
        }
        return jsonArray.toString();
    }

    @PostMapping("/{id}/like")
    public String likeComment(@PathVariable("id") long id) {
        if (dataBase.findComment(id) == null) throw new CustomException("Comment Id Doesn't Exist");
        dataBase.likeComment(id);
        return "successfully liked";
    }

    @PostMapping("/{id}/dislike")
    public String dislikeComment(@PathVariable("id") long id) {
        if (dataBase.findComment(id) == null) throw new CustomException("Comment Id Doesn't Exist");
        dataBase.dislikeComment(id);
        return "successfully disliked";
    }
}
