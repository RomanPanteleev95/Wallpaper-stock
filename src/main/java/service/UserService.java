package service;

import model.User;
import model.Wallpaper;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public void saveUser(User user);
    public User getUserById(int id);
    public void update(User user);
    public List<Wallpaper> getAllImage();
}
