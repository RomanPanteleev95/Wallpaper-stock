package com.dao;

import com.dao.interfaces.UserDao;
import com.model.User;
import com.model.CollectionWallpaper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository

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
        session.save(user);
        ts.commit();
        session.close();
        return true;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        List<User> users = (List<User>) session.createQuery("from com.model.User").list();
        session.close();
        return users;
    }

    @Override
    @Transactional
    public List<CollectionWallpaper> getAllImage() {
        Session session = sessionFactory.getCurrentSession();
        List<CollectionWallpaper> collectionWallpapers =  (List<CollectionWallpaper>) session.createSQLQuery("select wallpapers.id, url from users\n" +
                "inner join users_wallpapers on users.id = users_wallpapers.user_id\n" +
                "inner join wallpapers on users_wallpapers.wallpaper_id = wallpapers.id").list();
        return collectionWallpapers;
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        Session session = sessionFactory.openSession();
        User user = (User)session.get(User.class, new Integer(id));
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
    @Transactional
    public void update(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        session.update(user);
        ts.commit();
        session.close();
    }


}
