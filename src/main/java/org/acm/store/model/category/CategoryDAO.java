package org.acm.store.model.category;

import java.util.List;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

public interface CategoryDAO {

    List<Category> getAllCategories();

    Category getCategory(long id);

    Category getCategory(String name);

    void addCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(long id);
}
