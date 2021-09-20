package org.acm.store.model.product;

import org.acm.store.model.category.Category;
import java.util.List;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

public interface ProductDAO {

    public List<Product> getAllProduct();

    public Product getProduct(long id);

    public List<Product> getProduct(String title);

    public List<Product> getProduct(Category category);

    public Product getProduct(String title, Category category);

    public void addProduct(Product product);

    public void updateProduct(Product product);

    public void deleteProduct(long id);
}
