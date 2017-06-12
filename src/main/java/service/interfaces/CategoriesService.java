package service.interfaces;

import model.Category;

import java.util.List;

public interface CategoriesService {
    public List<Category> getAllCategories();
    public void save(Category category);
    public Category getCategoryByName(String name);
}
