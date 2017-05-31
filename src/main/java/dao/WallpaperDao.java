package dao;


import model.Wallpaper;

public interface WallpaperDao {
    public void save(Wallpaper wallpaper);
    public Wallpaper getWallpaperById(int id);
}
