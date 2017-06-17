package com.service.interfaces;

import com.model.CollectionWallpaper;

import java.util.List;

public interface CollectionWallpaperService {
    public void save(CollectionWallpaper collectionWallpaper);
    public CollectionWallpaper getWallpaperById(int id);
    public List<CollectionWallpaper> getAllWallpapers();
    public CollectionWallpaper getWallpaperByUrl(String url);
    public void update(CollectionWallpaper collectionWallpaper);
}
