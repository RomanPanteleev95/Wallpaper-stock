package dao;

import dao.interfaces.DefaultWallpapersDao;
import model.CollectionWallpaper;
import model.DefaultWallpaper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

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
    public void save(DefaultWallpaper defaultWallpapers) {
        Session session = sessionFactory.getCurrentSession();
        Transaction ts = session.beginTransaction();
        session.save(defaultWallpapers);
        ts.commit();
    }
}
