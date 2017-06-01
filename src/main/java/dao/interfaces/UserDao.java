package dao.interfaces;

import model.User;
import model.CollectionWallpaper;

import java.util.List;

public interface UserDao {
    public boolean saveUser(User user);
    public List<User> getAllUsers();
    public List<CollectionWallpaper> getAllImage();
    public User getUserById(int id);
    public User getUserByLogin(String login);
//    public void remove(int id);
    public void update(User user);
}
