package com.dao;

import com.dao.interfaces.DefaultWallpapersDao;
import com.model.Category;
import com.model.DefaultWallpaper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;
import java.util.List;

@Repository
public class DefaultWallpapersDaoImpl implements DefaultWallpapersDao{

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    @Transactional
    public void save(DefaultWallpaper defaultWallpapers) {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        session.save(defaultWallpapers);
        ts.commit();
        session.close();
    }

    @Override
    @Transactional
    public List<DefaultWallpaper> getAllDefaultWallpapers() {
        Session session = sessionFactory.openSession();
        List<DefaultWallpaper> defaultWallpapers = (List<DefaultWallpaper>)session.createQuery("from com.model.DefaultWallpaper").list();
        session.close();
        return defaultWallpapers;
    }

    @Override
    @Transactional
    public void delete() {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        int cnt = session.createSQLQuery("delete from default_wallpapers where id > 0").executeUpdate();
        ts.commit();
        session.close();
    }

    @Override
    public List<DefaultWallpaper> getWallpapersByCategory(Category category) {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        List<DefaultWallpaper> defaultWallpapers = (List<DefaultWallpaper>)session.createSQLQuery("select default_wallpapers.id, url from categories \n" +
                "inner join categories_default_wallpapers on categories.id = categories_default_wallpapers.categories_id\n" +
                "inner join default_wallpapers on categories_default_wallpapers.default_wallpapers_id = default_wallpapers.id where categories.name = :name").setString("name", category.getName()).list();
        ts.commit();
        session.close();
        while (defaultWallpapers.size() > 30)
            defaultWallpapers.remove(defaultWallpapers.size() - 1);
        return defaultWallpapers;
    }
}
