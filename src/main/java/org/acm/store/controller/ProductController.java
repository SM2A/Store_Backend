package org.acm.store.controller;

import org.acm.store.controller.util.CustomException;
import org.acm.store.model.DataBase;
import org.acm.store.model.category.Category;
import org.acm.store.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    DataBase dataBase;

    @GetMapping
    public List<Product> getProducts() {
        return dataBase.getProducts();
    }

    @PostMapping("/search/name")
    public List<Product> searchProductByName(@RequestParam(required = false) @NotBlank @Valid String name) {
        return dataBase.searchProductsByName(name);
    }

    @PostMapping("/search/category")
    public List<Product> searchProductByCategory(@RequestParam(required = false) @NotBlank @Valid String category) {
        return dataBase.searchProductsByCategory(category);
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam(required = false) @NotBlank @Valid String title,
                             @RequestParam(required = false) @NotBlank @Valid String description,
                             @RequestParam(required = false) @NotBlank @Valid String quantityAvailable,
                             @RequestParam(required = false) @NotBlank @Valid String price,
                             @RequestParam(required = false) @NotBlank @Valid String category,
                             @RequestParam(required = false) @NotBlank @Valid String imgAddress) {

        dataBase.addProduct(title, description, Integer.parseInt(quantityAvailable),
                Integer.parseInt(price), category, imgAddress);
        return "The product has been successfully added.";
    }

    @GetMapping("/{id}")
    public Product findProductById(@PathVariable("id") long id) {
        if (dataBase.findProduct(id) == null) throw new CustomException("Product Id Doesn't Exist");
        return dataBase.findProduct(id);
    }

    @PostMapping("/rate")
    public String rateProduct(@RequestParam(required = false) @NotBlank @Valid String productId,
                              @RequestParam(required = false) @NotBlank @Valid String rating) {

        dataBase.addRatingToProduct(Long.parseLong(productId), Integer.parseInt(rating));
        return "The product has been successfully rated";
    }

    @PostMapping("/category/add")
    public String addCategory(@RequestParam(required = false) @NotBlank @Valid String name) {
        dataBase.addCategory(name);
        return "Category add successfully";
    }

    @GetMapping("/category")
    public List<Category> getCategories() {
        return dataBase.getCategories();
    }

    @PostMapping("/edit")
    public String editProduct(@RequestParam(required = false) @NotBlank @Valid String id,
                              @RequestParam(required = false) @NotBlank @Valid String title,
                              @RequestParam(required = false) @NotBlank @Valid String description,
                              @RequestParam(required = false) @NotBlank @Valid String quantityAvailable,
                              @RequestParam(required = false) @NotBlank @Valid String price,
                              @RequestParam(required = false) @NotBlank @Valid String category,
                              @RequestParam(required = false) @NotBlank @Valid String imgAddress) {

        dataBase.editProduct(Long.parseLong(id), title, description, Integer.parseInt(quantityAvailable),
                Integer.parseInt(price), category, imgAddress);
        return "Edited successfully";
    }
}
