package org.acm.store.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.acm.store.model.Comment;
import org.acm.store.model.DataBase;
import org.acm.store.model.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@RequestMapping("/comments")
public class CommentController {
    @GetMapping
    public ArrayList<Comment> getComments(){
        return DataBase.getInstance().getAllComments();
    }

    @PostMapping("/add")
    public String addComment(@RequestParam long productID, @RequestParam String text, HttpServletRequest request){
        if (!Authentication.isLogin(request)) return "Please login first";
        if (Authentication.isAdmin(Authentication.loggedInUser(request))) return "Make sure you are a costumer";
        User user = Authentication.loggedInUser(request);
        DataBase.getInstance().addComment(user.getID(), productID, text);
        return "Your comment has been successfully added.";
    }

    @GetMapping("/{productId}")
    public String showProductComments(@PathVariable("productId") long productId){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List list = Stream.of(DataBase.getInstance().getProductComments(productId)).collect(Collectors.toList());
        return gson.toJson(list);
    }

    @PostMapping("/{id}/like")
    public String likeComment(@PathVariable("id") long id, HttpServletRequest request){
        if (!Authentication.isLogin(request)) return "Please login first";
        if (Authentication.isAdmin(Authentication.loggedInUser(request))) return "Make sure you are a costumer";
        //exception handling comment id doesn't exist
        DataBase.getInstance().likeComment(id);
        return "successfully liked";
    }

    @PostMapping("/{id}/dislike")
    public String dislikeComment(@PathVariable("id") long id, HttpServletRequest request){
        if (!Authentication.isLogin(request)) return "Please login first";
        if (Authentication.isAdmin(Authentication.loggedInUser(request))) return "Make sure you are a costumer";
        //exception handling comment id doesn't exist
        DataBase.getInstance().dislikeComment(id);
        return "successfully disliked";
    }

}
