package com.dao;

import com.dao.interfaces.DefaultWallpapersDao;
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
        session.save(defaultWallpapers);
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
}
