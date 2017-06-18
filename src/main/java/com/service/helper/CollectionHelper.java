package com.service.helper;

import com.model.Category;
import com.model.CollectionWallpaper;
import com.service.interfaces.CategoriesService;
import com.service.interfaces.CollectionWallpaperService;

import java.util.List;
import java.util.Set;

public class CollectionHelper {
    public static CollectionWallpaper getCurrentWallpaper(CollectionWallpaper collectionWallpaper, CollectionWallpaperService collectionWallpaperService, CategoriesService categoriesService){
        if (isExist(collectionWallpaper, collectionWallpaperService) != null)
            return isExist(collectionWallpaper, collectionWallpaperService);
        return addWallpaperToCollection(collectionWallpaper, categoriesService);
    }

    private static CollectionWallpaper isExist(CollectionWallpaper collectionWallpaper, CollectionWallpaperService collectionWallpaperService){
        List<CollectionWallpaper> collectionWallpapers = collectionWallpaperService.getAllWallpapers();
        for (CollectionWallpaper cw : collectionWallpapers)
            if(collectionWallpaper.equals(cw)){
                return cw;
            }
            return null;
    }

    private static CollectionWallpaper addWallpaperToCollection(CollectionWallpaper collectionWallpaper, CategoriesService categoriesService){
        Set<Category> currentCategories = collectionWallpaper.getCategories();
        List<Category> categoryFromDataBase = categoriesService.getAllCategories();
        for (Category category : categoryFromDataBase)
            if (currentCategories.contains(category)) {
                currentCategories.remove(category);
                currentCategories.add(category);
            }
        collectionWallpaper.setCategories(currentCategories);
        return collectionWallpaper;
    }
}
