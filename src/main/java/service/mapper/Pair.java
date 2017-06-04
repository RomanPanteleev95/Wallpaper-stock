package service.mapper;

import model.CollectionWallpaper;
import model.DefaultWallpaper;
import model.User;

public class Pair {
    private User user;
    private CollectionWallpaper collectionWallpaper;

    public Pair(User user,CollectionWallpaper collectionWallpaper){
        this.user = user;
        this.collectionWallpaper = collectionWallpaper;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CollectionWallpaper getCollectionWallpaper() {
        return collectionWallpaper;
    }

    public void setCollectionWallpaper(CollectionWallpaper collectionWallpaper) {
        this.collectionWallpaper = collectionWallpaper;
    }
}
