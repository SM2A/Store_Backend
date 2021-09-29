package org.acm.store.model.product;

import org.acm.store.model.category.Category;
import java.util.List;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

public interface ProductDAO {

    List<Product> getAllProduct();

    Product getProduct(long id);

    List<Product> getProduct(String title);

    List<Product> getProduct(Category category);

    Product getProduct(String title, Category category);

    long addProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(long id);
}
