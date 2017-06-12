package service;

import dao.interfaces.CollectionWallpapersDao;
import model.CollectionWallpaper;
import service.interfaces.CollectionWallpaperService;

import java.util.List;

public class CollectionWallpaperServiceImpl implements CollectionWallpaperService{
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
