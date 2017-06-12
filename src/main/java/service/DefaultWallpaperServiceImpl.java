package service;

import dao.interfaces.DefaultWallpapersDao;
import model.DefaultWallpaper;
import service.interfaces.DefaultWallpaperService;


public class DefaultWallpaperServiceImpl implements DefaultWallpaperService {
    DefaultWallpapersDao defaultWallpapersDao;

    public DefaultWallpapersDao getDefaultWallpapersDao() {
        return defaultWallpapersDao;
    }

    public void setDefaultWallpapersDao(DefaultWallpapersDao defaultWallpapersDao) {
        this.defaultWallpapersDao = defaultWallpapersDao;
    }

    @Override
    public void save(DefaultWallpaper defaultWallpaper) {
        this.defaultWallpapersDao.save(defaultWallpaper);
    }
}
