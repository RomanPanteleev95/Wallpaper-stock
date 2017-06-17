package com.dao.interfaces;

import com.model.Category;
import java.util.List;

public interface CategoriesDao {
    public List<Category> getAllCategories();
    public void save(Category category);
    public Category getCategoryByName(String name);
}
