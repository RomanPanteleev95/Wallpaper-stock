package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Time;
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


    @Column(name = "old_password")
    private String oldPassword;

    @Column(name = "new_password")
    private String newPassword;

    @Column(name = "session_id")
    private int sessionId;

    @Column(name = "last_active_time")
    private String lastActiveTime;



    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name = "users_wallpapers", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "wallpaper_id"))
    private Set<CollectionWallpaper> collectionWallpapers = new HashSet<CollectionWallpaper>();

    public User(){
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public Set<CollectionWallpaper> getCollectionWallpapers() {
        return collectionWallpapers;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setCollectionWallpapers(Set<CollectionWallpaper> collectionWallpapers) {
        this.collectionWallpapers = collectionWallpapers;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(String lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }


    @Override
    public boolean equals(Object obj) {
        User user = (User)obj;
        if(this.login.equals(user.login))
            return true;
        else
            return false;
    }

    @Override
    public String toString(){
        return "{\n" +
                    "\t\"id\":" + id + ",\n" +
                    "\t\"login\":" + login + ",\n" +
                    "\t\"old_password\":" + oldPassword + "\n" +
                    "\t\"new_password\":" + newPassword + "\n" +
                    "\t\"session_id\":" + sessionId + "\n" +
                    "\t\"last_active_time\":" + lastActiveTime + "\n" +
                "}\n";
    }
}
