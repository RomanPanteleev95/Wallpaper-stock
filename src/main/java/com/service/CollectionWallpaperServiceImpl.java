package com.service;

import com.dao.interfaces.CollectionWallpapersDao;
import com.model.CollectionWallpaper;
import org.springframework.beans.factory.annotation.Autowired;
import com.service.interfaces.CollectionWallpaperService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionWallpaperServiceImpl implements CollectionWallpaperService{

    @Autowired
    CollectionWallpapersDao collectionWallpapersDao;

    public CollectionWallpapersDao getCollectionWallpapersDao() {
        return collectionWallpapersDao;
    }

    public void setCollectionWallpapersDao(CollectionWallpapersDao collectionWallpapersDao) {
        this.collectionWallpapersDao = collectionWallpapersDao;
    }

    @Override
    public void save(CollectionWallpaper collectionWallpaper) {
        this.collectionWallpapersDao.save(collectionWallpaper);
    }

    @Override
    public CollectionWallpaper getWallpaperById(int id) {
        return this.collectionWallpapersDao.getWallpaperById(id);
    }

    @Override
    public List<CollectionWallpaper> getAllWallpapers() {
        return this.collectionWallpapersDao.getAllWallpapers();
    }

    @Override
    public CollectionWallpaper getWallpaperByUrl(String url) {
        return this.collectionWallpapersDao.getWallpaperByUrl(url);
    }

    @Override
    public void update(CollectionWallpaper collectionWallpaper) {
        this.collectionWallpapersDao.update(collectionWallpaper);
    }
}
