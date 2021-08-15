package org.acm.store.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.acm.store.model.Comment;
import org.acm.store.model.DataBase;
import org.acm.store.model.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@Validated
@RestController
@RequestMapping("/comments")
public class CommentController {
    @GetMapping
    public ArrayList<Comment> getComments() {
        //User user = DataBase.getInstance().validateUser(json.get("email").asText(), json.get("password").asText());
        //if(user instanceof Admin)--->exception handling
        return DataBase.getInstance().getAllComments();
    }

    @PostMapping("/add")
    public void addComment(@RequestBody ObjectNode json) {
        //todo change RequestBody to RequestParam
        User user = DataBase.getInstance().validateUser(json.get("email").asText(), json.get("password").asText());
        //if(user instanceof Costumer)--->exception handling
        DataBase.getInstance().addComment(user.getID(), json.get("productID").asLong(), json.get("text").asText());
    }

    @GetMapping("/{id}")
    public Comment showComment(@PathVariable("id") long id) {
        return DataBase.getInstance().findCommentByID(id);
    }

    @PostMapping("/{id}/like")
    public void likeComment(@PathVariable("id") long id) {
        //exception handling
        DataBase.getInstance().likeComment(id);
    }

    @PostMapping("/{id}/dislike")
    public void dislikeComment(@PathVariable("id") long id) {
        //exception handling
        DataBase.getInstance().dislikeComment(id);
    }
}
