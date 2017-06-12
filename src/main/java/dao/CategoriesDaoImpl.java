package dao;

import dao.interfaces.CategoriesDao;
import model.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoriesDaoImpl implements CategoriesDao {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Category> getAllCategories() {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        List<Category> categories = (List<Category>)session.createQuery("from model.Category").list();
        ts.commit();
        session.close();
        return categories;
    }

    @Override
    public void save(Category category) {
        Session session = this.sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        session.persist(category);
        ts.commit();
        session.close();
    }

    @Override
    public Category getCategoryByName(String name) {
        Session session = sessionFactory.openSession();
        List<Category> categories = this.getAllCategories();
        Category category = new Category();
        for (Category c : categories)
            if (c.getName().equals(name))
                category = c;
        session.close();
        return category;
    }
}
