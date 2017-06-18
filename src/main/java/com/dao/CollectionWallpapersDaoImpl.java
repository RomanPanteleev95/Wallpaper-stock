package com.dao;

import com.dao.interfaces.CollectionWallpapersDao;
import com.model.CollectionWallpaper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CollectionWallpapersDaoImpl implements CollectionWallpapersDao {

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
    public void save(CollectionWallpaper collectionWallpaper) {
        Session session = this.sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        session.save(collectionWallpaper);
        ts.commit();
        session.close();
    }

    @Override
    public CollectionWallpaper getWallpaperById(int id) {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        CollectionWallpaper collectionWallpaper = (CollectionWallpaper) session.get(CollectionWallpaper.class, new Integer(id));
        ts.commit();
        session.close();
        return collectionWallpaper;
    }

    @Override
    public List<CollectionWallpaper> getAllWallpapers() {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        List<CollectionWallpaper> collectionWallpapers = (List<CollectionWallpaper>) session.createQuery("from com.model.CollectionWallpaper").list();
        ts.commit();
        session.close();
        return collectionWallpapers;
    }

    @Override
    public CollectionWallpaper getWallpaperByUrl(String url) {
        Session session = sessionFactory.openSession();
        List<CollectionWallpaper> wallpapers = this.getAllWallpapers();
        CollectionWallpaper collectionWallpaper = new CollectionWallpaper();
        for (CollectionWallpaper cw : wallpapers)
            if (cw.getUrl().equals(url))
                collectionWallpaper = cw;
        session.close();
        return collectionWallpaper;
    }

    @Override
    public void update(CollectionWallpaper collectionWallpaper) {
        Session session = this.sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        session.update(collectionWallpaper);
        ts.commit();
        session.close();
    }
}
