package dao;

import dao.interfaces.UserDao;
import model.User;
import model.CollectionWallpaper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
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
    public boolean saveUser(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        session.persist(user);
        ts.commit();
        session.close();
        return true;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        List<User> users = (List<User>) session.createQuery("from model.User").list();
        ts.commit();
        session.close();
        return users;
    }

    @Override
    public List<CollectionWallpaper> getAllImage() {
        Session session = sessionFactory.getCurrentSession();
        Transaction ts = session.beginTransaction();
        List<CollectionWallpaper> collectionWallpapers =  (List<CollectionWallpaper>) session.createSQLQuery("select wallpapers.id, url from users\n" +
                "inner join users_wallpapers on users.id = users_wallpapers.user_id\n" +
                "inner join wallpapers on users_wallpapers.wallpaper_id = wallpapers.id").list();
        ts.commit();
        return collectionWallpapers;
    }

    @Override
    public User getUserById(int id) {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        User user = (User)session.get(User.class, new Integer(id));
        ts.commit();
        session.close();
        return user;
    }

    @Override
    public User getUserByLogin(String login) {
        Session session = sessionFactory.openSession();
        List<User> users = this.getAllUsers();
        User user = new User();
        for (User u : users)
            if (u.getLogin().equals(login))
                user = u;
        session.close();
        return user;
    }

    @Override
    public void update(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        session.update(user);
        session.close();
        ts.commit();
    }


}
