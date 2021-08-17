package org.acm.store.controller;

import org.acm.store.controller.validation.Authentication;
import org.acm.store.controller.validation.CustomException;
import org.acm.store.model.DataBase;
import org.acm.store.model.Product;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

@Validated
@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public ArrayList<Product> getProducts(){
        return DataBase.getInstance().getProducts();
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam(required = false) @NotBlank @Valid String title,
                             @RequestParam(required = false) @NotBlank @Valid String description,
                             @RequestParam(required = false) @NotBlank @Valid String quantityAvailable,
                             @RequestParam(required = false) @NotBlank @Valid String price,
                             @RequestParam(required = false) @NotBlank @Valid String category,
                             HttpServletRequest request) {
        if (!Authentication.isLogin(request)) throw new CustomException("please login first");
        if (!Authentication.isAdmin(Authentication.loggedInUser(request))) throw new CustomException("You dont have permission");
        DataBase.getInstance().addProduct(title, description, Integer.parseInt(quantityAvailable),
                Integer.parseInt(price), category);
        return "The product has been successfully added.";
    }

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable("id") long id){
        if(DataBase.getInstance().findProduct(id) == null) throw new CustomException("Product Id Doesn't Exist.");
        return DataBase.getInstance().findProduct(id);
    }

    @PostMapping("/rate")
    public String rateProduct(@RequestParam(required = false) @NotBlank @Valid String productId,
                              @RequestParam(required = false) @NotBlank @Valid String rating,
                              HttpServletRequest request){
        if (!Authentication.isLogin(request)) throw new CustomException("please login first");
        if(DataBase.getInstance().findProduct(Long.parseLong(productId)) == null) throw new CustomException("Product Id Doesn't Exist.");
        DataBase.getInstance().addRatingToProduct(Long.parseLong(productId), Integer.parseInt(rating));
        return "The product has been successfully rated.";
    }
}
