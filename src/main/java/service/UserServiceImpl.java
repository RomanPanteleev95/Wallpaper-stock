package service;

import dao.interfaces.UserDao;
import model.User;
import model.CollectionWallpaper;
import service.interfaces.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
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
