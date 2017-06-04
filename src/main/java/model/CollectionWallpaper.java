package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "collection_wallpapers")
public class CollectionWallpaper {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "url")
    private String url;

    @Column(name = "width")
    private int width;

    @Column(name = "height")
    private int height;

    @Column(name = "author")
    private String author;

    @Column(name = "likes")
    private int likes;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name = "users_wallpapers",  joinColumns = @JoinColumn(name = "wallpaper_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<User>();

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name = "categories_collection_wallpapers",  joinColumns = @JoinColumn(name = "collection_wallpapers_id"), inverseJoinColumns = @JoinColumn(name = "categories_id"))
    private Set<Category> categories = new HashSet<>();

    public CollectionWallpaper(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }


    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object obj) {
        CollectionWallpaper collectionWallpaper = (CollectionWallpaper) obj;
        if(this.url.equals(collectionWallpaper.url))
            return true;
        else
            return false;
    }

    @Override
    public String toString(){
        return id + " " + url;
    }
}
