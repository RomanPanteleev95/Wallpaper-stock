package com.controller;

import com.dao.interfaces.CollectionWallpapersDao;
import com.dao.interfaces.DefaultWallpapersDao;
import com.model.Category;
import com.model.CollectionWallpaper;
import com.model.DefaultWallpaper;
import com.model.User;
import com.service.interfaces.DefaultWallpaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.service.dto.UsersWalpaper;
import com.service.interfaces.CategoriesService;
import com.service.interfaces.CollectionWallpaperService;
import com.service.interfaces.UserService;
import com.service.mapper.UsersWallpaperMapper;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/wallpapers")
public class DefaultWallpaperController {

    @Autowired
    UserService userService;
    @Autowired
    CollectionWallpaperService collectionWallpaperService;
    @Autowired
    CategoriesService categoriesService;
    @Autowired
    DefaultWallpaperService defaultWallpaperService;

    @RequestMapping(value = "/addWallpToCollection", method = RequestMethod.POST)
    public User addWallpToCollection(@Valid @RequestBody UsersWalpaper usersWalpaper){
        User user = UsersWallpaperMapper.getUSer(usersWalpaper);
        CollectionWallpaper collectionWallpaper = UsersWallpaperMapper.getWallpaper(usersWalpaper);

        //create class CollectionHelper
        boolean isExist = false;
        List<CollectionWallpaper> collectionWallpapers = collectionWallpaperService.getAllWallpapers();
        if (collectionWallpapers.contains(collectionWallpaper))
            isExist = true;

        if (!isExist) {
            Set<Category> cS = new HashSet<>();
            cS = collectionWallpaper.getCategories();
            Set<Category> emptySet = new HashSet<>();
            collectionWallpaper.setCategories(emptySet);
            collectionWallpaperService.save(collectionWallpaper);
            collectionWallpaper = collectionWallpaperService.getWallpaperByUrl(collectionWallpaper.getUrl());
            List<Category> categoryList = categoriesService.getAllCategories();
            Category[] ctgArr = cS.toArray(new Category[cS.size()]);
            for (Category ctg : ctgArr){
                Category ctgTmp = ctg;
                for (Category ctgL : categoryList)
                    if (ctg.equals(ctgL))
                        ctgTmp = ctgL;
                emptySet.add(ctgTmp);
            }
            collectionWallpaper = collectionWallpaperService.getWallpaperByUrl(collectionWallpaper.getUrl());
            collectionWallpaper.setCategories(emptySet);

            collectionWallpaperService.update(collectionWallpaper);

        }

        Set<CollectionWallpaper> set = user.getCollectionWallpapers();
        set.add(collectionWallpaper);
        user.setCollectionWallpapers(set);
        userService.update(user);
        return user;
    }

    @RequestMapping(value = "/addDefault", method = RequestMethod.POST)
    public String addNewWallp(@Valid @RequestBody List<DefaultWallpaper> defaultWallpapers){
        for (DefaultWallpaper dw : defaultWallpapers){
            List<Category> categories = categoriesService.getAllCategories();
            Set<Category> dwCategories = dw.getCategories();
            for (Category category : categories)
                if (dwCategories.contains(category)) {
                    dwCategories.remove(category);
                    dwCategories.add(category);
                }
            dw.setCategories(dwCategories);
            defaultWallpaperService.save(dw);
        }
        return "YES";
    }

    @RequestMapping(value = "/showAllDefault", method = RequestMethod.GET)
    public List<DefaultWallpaper> showAllDefault(){
        return defaultWallpaperService.getAllDefaultWallpapers();
    }
}
