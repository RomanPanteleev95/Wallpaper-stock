package com.service;

import com.dao.interfaces.DefaultWallpapersDao;
import com.model.DefaultWallpaper;
import com.service.interfaces.DefaultWallpaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultWallpaperServiceImpl implements DefaultWallpaperService {
    @Autowired
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

    @Override
    public List<DefaultWallpaper> getAllDefaultWallpapers() {
        return this.defaultWallpapersDao.getAllDefaultWallpapers();
    }

    @Override
    public void delete() {
        this.defaultWallpapersDao.delete();
    }
}
