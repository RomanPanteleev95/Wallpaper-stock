package dao;

import dao.interfaces.CollectionWallpapersDao;
import model.CollectionWallpaper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
    public void save(CollectionWallpaper collectionWallpaper) {
        Session session = sessionFactory.getCurrentSession();
        Transaction ts = session.beginTransaction();
        session.persist(collectionWallpaper);
        ts.commit();
    }

    @Override
    public CollectionWallpaper getWallpaperById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction ts = session.beginTransaction();
        CollectionWallpaper collectionWallpaper = (CollectionWallpaper) session.get(CollectionWallpaper.class, new Integer(id));
        ts.commit();
        return collectionWallpaper;
    }

    @Override
    public List<CollectionWallpaper> getAllWallpapers() {
        Session session = sessionFactory.getCurrentSession();
        Transaction ts = session.beginTransaction();
        List<CollectionWallpaper> collectionWallpapers = (List<CollectionWallpaper>) session.createQuery("from model.CollectionWallpaper").list();
        ts.commit();
        return collectionWallpapers;
    }
}
