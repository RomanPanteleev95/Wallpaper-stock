package dao.interfaces;


import model.CollectionWallpaper;

import java.util.List;

public interface CollectionWallpapersDao {
    public void save(CollectionWallpaper collectionWallpaper);
    public CollectionWallpaper getWallpaperById(int id);
    public List<CollectionWallpaper> getAllWallpapers();
    public CollectionWallpaper getWallpaperByUrl(String url);
    public void update(CollectionWallpaper collectionWallpaper);
}
