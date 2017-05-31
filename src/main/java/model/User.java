package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.SessionFactory;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "login")
    private String login;


    @Column(name = "pass")
    private String password;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name = "users_wallpapers", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "wallpaper_id"))
    private Set<Wallpaper> wallpapers = new HashSet<Wallpaper>();

    public User(){
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Set<Wallpaper> getWallpapers() {
        return wallpapers;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setWallpapers(Set<Wallpaper> wallpapers) {
        this.wallpapers = wallpapers;
    }

    @Override
    public String toString(){
        return "{\n" +
                    "\t\"id\":" + id + ",\n" +
                    "\t\"login\":" + login + ",\n" +
                    "\t\"password\":" + password + "\n" +
                "}\n";
    }
}
