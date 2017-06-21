package com.dao.interfaces;


import com.model.Category;
import com.model.DefaultWallpaper;

import java.util.List;

public interface DefaultWallpapersDao {
    public void save(DefaultWallpaper defaultWallpaper);
    public List<DefaultWallpaper> getAllDefaultWallpapers();
    public void delete();
    public List<DefaultWallpaper> getWallpapersByCategory(Category category);
}
