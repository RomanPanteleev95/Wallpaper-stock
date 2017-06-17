package com.service;

import com.dao.interfaces.UserDao;
import com.model.User;
import com.model.CollectionWallpaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.interfaces.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public boolean saveUser(User user) {
        return this.userDao.saveUser(user);
    }

    @Override
    public User getUserById(int id) {
        return this.userDao.getUserById(id);
    }

    @Override
    public void update(User user) {
        this.userDao.update(user);
    }

    @Override
    public List<CollectionWallpaper> getAllImage() {
        return this.userDao.getAllImage();
    }

    @Override
    public User getUserByLogin(String login) {
        return this.userDao.getUserByLogin(login);
    }
}
