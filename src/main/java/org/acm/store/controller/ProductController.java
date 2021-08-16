package org.acm.store.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.acm.store.controller.validation.Authentication;
import org.acm.store.model.DataBase;
import org.acm.store.model.Product;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public String getProducts(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List list = Stream.of(DataBase.getInstance().getProducts()).collect(Collectors.toList());
        return gson.toJson(list);
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam(required = false) @NotBlank @Valid String title,
                             @RequestParam(required = false) @NotBlank @Valid String description,
                             @RequestParam(required = false) @NotBlank @Valid int quantityAvailable,
                             @RequestParam(required = false) @NotBlank @Valid int price,
                             @RequestParam(required = false) @NotBlank @Valid String category,
                             HttpServletRequest request){
        if (!Authentication.isLogin(request)) return "please login first";
        if (!Authentication.isAdmin(Authentication.loggedInUser(request))) return "You dont have permission";
        DataBase.getInstance().addProduct(title, description, quantityAvailable, price, category);
        return "The product has been successfully added.";
    }

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable("id") long id){
        return DataBase.getInstance().findProduct(id);
    }

    @PostMapping("/rate")
    public String rateProduct(@RequestParam(required = false) @NotBlank @Valid long productId,
                              @RequestParam(required = false) @NotBlank @Valid int rating,
                              HttpServletRequest request){
        if (!Authentication.isLogin(request)) return "please login first";
        DataBase.getInstance().addRatingToProduct(productId, rating);
        return "The product has been successfully rated.";
    }
}
