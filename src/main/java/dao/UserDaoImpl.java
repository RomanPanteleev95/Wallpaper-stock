package dao;

import model.User;
import model.Wallpaper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserDaoImpl implements UserDao{

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
    public void saveUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        Transaction ts = session.beginTransaction();
        session.persist(user);
        ts.commit();
//        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        Transaction ts = session.beginTransaction();
        List<User> users = (List<User>) session.createQuery("from model.User").list();
        ts.commit();
        return users;
    }

    @Override
    public List<Wallpaper> getAllImage() {
        Session session = sessionFactory.getCurrentSession();
        Transaction ts = session.beginTransaction();
        List<Wallpaper> wallpapers =  (List<Wallpaper>) session.createSQLQuery("select wallpapers.id, url from users\n" +
                "inner join users_wallpapers on users.id = users_wallpapers.user_id\n" +
                "inner join wallpapers on users_wallpapers.wallpaper_id = wallpapers.id").list();
        ts.commit();
        return wallpapers;
    }

    @Override
    public User getUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction ts = session.beginTransaction();
        User user = (User)session.get(User.class, new Integer(id));
        ts.commit();
//        session.close();
        return user;
    }

    @Override
    public void update(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        Transaction ts = session.beginTransaction();
        session.update(user);
        ts.commit();
    }


}
