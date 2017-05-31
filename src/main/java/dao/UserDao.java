package dao;

import model.User;
import model.Wallpaper;

import java.util.List;

public interface UserDao {
    public void saveUser(User user);
    public List<User> getAllUsers();
    public List<Wallpaper> getAllImage();
    public User getUserById(int id);
//    public void remove(int id);
    public void update(User user);
}
