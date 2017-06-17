package com.dao.interfaces;


import com.model.DefaultWallpaper;

import java.util.List;

public interface DefaultWallpapersDao {
    public void save(DefaultWallpaper defaultWallpaper);
    public List<DefaultWallpaper> getAllDefaultWallpapers();
}
