package com.service.interfaces;


import com.model.DefaultWallpaper;

import java.util.List;

public interface DefaultWallpaperService {
    public void save(DefaultWallpaper defaultWallpaper);
    public List<DefaultWallpaper> getAllDefaultWallpapers();
}
