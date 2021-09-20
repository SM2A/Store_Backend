package org.acm.store.model.category;

import java.util.List;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

public interface CategoryDAO {

    public List<Category> getAllCategories();

    public Category getCategory(long id);

    public Category getCategory(String name);

    public void addCategory(Category category);

    public void updateCategory(Category category);

    public void deleteCategory(long id);
}
