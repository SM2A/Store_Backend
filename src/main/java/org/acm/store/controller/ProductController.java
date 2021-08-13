package org.acm.store.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.acm.store.model.DataBase;
import org.acm.store.model.Product;
import org.acm.store.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/products")
public class ProductController {
    @GetMapping
    public ArrayList<Product> getProducts(){
        return DataBase.getInstance().getProducts();
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody ObjectNode json){
        User user = DataBase.getInstance().validateUser(json.get("email").asText(), json.get("password").asText());
        //if(user instanceof Admin)--->exception handling
        DataBase.getInstance().addProduct(json.get("title").asText(),json.get("description").asText(),
                json.get("quantityAvailable").asInt(), json.get("price").asInt(),
                json.get("category").asText());
    }

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable("id") long id){
        return DataBase.getInstance().findProduct(id);
    }

    @PostMapping("/rate")
    public void rateProduct(@RequestBody ObjectNode json){
        DataBase.getInstance().addRatingToProduct(json.get("productId").asLong(), json.get("rating").asInt());
    }
}
