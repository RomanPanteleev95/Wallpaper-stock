package service.mapper;

import model.CollectionWallpaper;
import model.DefaultWallpaper;

public class WallpaperMapper {
    public static CollectionWallpaper defaultToCollection(DefaultWallpaper defaultWallpaper){
        CollectionWallpaper collectionWallpaper = new CollectionWallpaper();
        collectionWallpaper.setCategories(defaultWallpaper.getCategories());
        collectionWallpaper.setHeight(defaultWallpaper.getHeight());
        collectionWallpaper.setWidth(defaultWallpaper.getWidth());
        collectionWallpaper.setUrl(defaultWallpaper.getUrl());
        collectionWallpaper.setLikes(defaultWallpaper.getLikes());
        collectionWallpaper.setAuthor(defaultWallpaper.getAuthor());

        return collectionWallpaper;
    }

    public static DefaultWallpaper collectionToDefault(CollectionWallpaper collectionWallpaper){
        DefaultWallpaper defaultWallpaper = new DefaultWallpaper();
        defaultWallpaper.setCategories(collectionWallpaper.getCategories());
        defaultWallpaper.setHeight(collectionWallpaper.getHeight());
        defaultWallpaper.setWidth(collectionWallpaper.getWidth());
        defaultWallpaper.setUrl(collectionWallpaper.getUrl());
        defaultWallpaper.setLikes(collectionWallpaper.getLikes());
        defaultWallpaper.setAuthor(collectionWallpaper.getAuthor());

        return defaultWallpaper;
    }
}
