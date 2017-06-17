package com.service;

import com.dao.interfaces.CategoriesDao;
import com.model.Category;
import com.service.interfaces.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    @Autowired
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
