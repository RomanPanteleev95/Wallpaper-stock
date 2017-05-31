package dao;

import model.Wallpaper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by pante on 28.05.2017.
 */
public class WallpaperDaoImpl implements WallpaperDao{

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Wallpaper wallpaper) {
        Session session = sessionFactory.getCurrentSession();
        Transaction ts = session.beginTransaction();
        session.persist(wallpaper);
        ts.commit();
    }

    @Override
    public Wallpaper getWallpaperById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction ts = session.beginTransaction();
        Wallpaper wallpaper = (Wallpaper) session.get(Wallpaper.class, new Integer(id));
        ts.commit();
        return wallpaper;
    }
}
