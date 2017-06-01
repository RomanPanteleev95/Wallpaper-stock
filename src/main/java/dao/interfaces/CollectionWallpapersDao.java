package dao.interfaces;


import model.CollectionWallpaper;

public interface CollectionWallpapersDao {
    public void save(CollectionWallpaper collectionWallpaper);
    public CollectionWallpaper getWallpaperById(int id);
}
