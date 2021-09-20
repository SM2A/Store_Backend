package org.acm.store.model.product;

import org.acm.store.model.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

@Service
public class ProductService {

    @Autowired
    ProductDAO productDAO;

    @Transactional
    public List<Product> getAllProduct() {
        return productDAO.getAllProduct();
    }

    @Transactional
    public Product getProduct(long id) {
        return productDAO.getProduct(id);
    }

    @Transactional
    public List<Product> getProduct(String title) {
        return productDAO.getProduct(title);
    }

    @Transactional
    public List<Product> getProduct(Category category) {
        return productDAO.getProduct(category);
    }

    @Transactional
    public Product getProduct(String title, Category category) {
        return productDAO.getProduct(title, category);
    }

    @Transactional
    public void addProduct(Product product) {
        productDAO.addProduct(product);
    }

    @Transactional
    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    @Transactional
    public void deleteProduct(long id) {
        productDAO.deleteProduct(id);
    }
}
