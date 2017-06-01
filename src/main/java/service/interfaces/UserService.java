package service.interfaces;

import model.User;
import model.CollectionWallpaper;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public boolean saveUser(User user);
    public User getUserById(int id);
    public void update(User user);
    public List<CollectionWallpaper> getAllImage();
    public User getUserByLogin(String login);
}
