package service;

import dao.UserDao;
import model.User;
import model.Wallpaper;

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
    public void saveUser(User user) {
        this.userDao.saveUser(user);
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
    public List<Wallpaper> getAllImage() {
        return this.userDao.getAllImage();
    }
}
