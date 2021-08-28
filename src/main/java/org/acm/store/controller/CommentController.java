package org.acm.store.controller;

import org.acm.store.controller.validation.Authentication;
import org.acm.store.controller.validation.CustomException;
import org.acm.store.model.Comment;
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
@RequestMapping("/comments")
public class CommentController {

    @GetMapping
    public ArrayList<Comment> getComments(){
        return DataBase.getInstance().getAllComments();
    }

    @PostMapping("/add")
    public String addComment(@RequestParam(required = false) @NotBlank @Valid String productID,
                             @RequestParam(required = false) @NotBlank @Valid String text,
                             HttpServletRequest request){
        if (!Authentication.isLogin(request)) throw new CustomException("please login first");
        if (Authentication.isAdmin(Authentication.loggedInUser(request)))
            throw new CustomException("Make sure you are a costumer");
        if(DataBase.getInstance().findProduct(Long.parseLong(productID)) == null)
            throw new CustomException("Product Id Doesn't Exist");
        User user = Authentication.loggedInUser(request);
        DataBase.getInstance().addComment(user.getID(), Long.parseLong(productID), text);
        return "Your comment has been successfully added";
    }

    @GetMapping("/{productId}")
    public ArrayList<Comment> showProductComments(@PathVariable("productId") long productId){
        if(DataBase.getInstance().findProduct(productId) == null)
            throw new CustomException("Product Id Doesn't Exist");
        return DataBase.getInstance().getProductComments(productId);
    }

    @PostMapping("/{id}/like")
    public String likeComment(@PathVariable("id") long id, HttpServletRequest request){
        if (!Authentication.isLogin(request)) throw new CustomException("please login first");
        if (Authentication.isAdmin(Authentication.loggedInUser(request)))
            throw new CustomException("Make sure you are a costumer");
        if (DataBase.getInstance().findComment(id) == null) throw new CustomException("Comment Id Doesn't Exist");
        DataBase.getInstance().likeComment(id);
        return "successfully liked";
    }

    @PostMapping("/{id}/dislike")
    public String dislikeComment(@PathVariable("id") long id, HttpServletRequest request){
        if (!Authentication.isLogin(request)) throw new CustomException("please login first");
        if (Authentication.isAdmin(Authentication.loggedInUser(request)))
            throw new CustomException("Make sure you are a costumer");
        if (DataBase.getInstance().findComment(id) == null) throw new CustomException("Comment Id Doesn't Exist.");
        DataBase.getInstance().dislikeComment(id);
        return "successfully disliked";
    }
}
