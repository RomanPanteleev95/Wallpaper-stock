package service;

import dao.interfaces.CategoriesDao;
import model.Category;
import service.interfaces.CategoriesService;

import java.util.List;

public class CategoriesServiceImpl implements CategoriesService {

    private CategoriesDao categoriesDao;

    public void setCategoriesDao(CategoriesDao categoriesDao) {
        this.categoriesDao = categoriesDao;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoriesDao.getAllCategories();
    }

    @Override
    public void save(Category category) {
        this.categoriesDao.save(category);
    }

    @Override
    public Category getCategoryByName(String name) {
        return this.categoriesDao.getCategoryByName(name);
    }
}
