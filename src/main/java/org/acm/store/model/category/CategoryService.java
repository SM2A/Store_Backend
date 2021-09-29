package org.acm.store.model.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */

@Service
public class CategoryService {

    @Autowired
    CategoryDAO categoryDAO;

    @Transactional
    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

    @Transactional
    public Category getCategory(long id) {
        return categoryDAO.getCategory(id);
    }

    @Transactional
    public Category getCategory(String name) {
        return categoryDAO.getCategory(name);
    }

    @Transactional
    public void addCategory(Category category) {
        categoryDAO.addCategory(category);
    }

    @Transactional
    public void updateCategory(Category category) {
        categoryDAO.updateCategory(category);
    }

    @Transactional
    public void deleteCategory(long id) {
        categoryDAO.deleteCategory(id);
    }
}
