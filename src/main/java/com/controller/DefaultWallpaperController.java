package com.controller;

import com.model.Category;
import com.model.CollectionWallpaper;
import com.model.DefaultWallpaper;
import com.model.User;
import com.service.helper.CollectionHelper;
import com.service.interfaces.DefaultWallpaperService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public User test(){
        User user = new User();
        Set<CollectionWallpaper> cw = new HashSet<>();
        CollectionWallpaper c1 = new CollectionWallpaper();
        c1.setUrl("000");
        cw.add(c1);
        CollectionWallpaper c2 = new CollectionWallpaper();
        c2.setUrl("001");
        cw.add(c2);
        user.setLogin("TESTER");
        user.setCollectionWallpapers(cw);
        userService.saveUser(user);

//        CollectionWallpaper collectionWallpaper = new CollectionWallpaper();
//        Set<Category> set = new HashSet<>();
//        Category category1 = new Category();
//        category1.setName("vvv");
//        set.add(category1);
//        collectionWallpaper.setUrl("....");
//        collectionWallpaper.setCategories(set);
//        collectionWallpaperService.save(collectionWallpaper);

//        DefaultWallpaper dw = new DefaultWallpaper();
//        Set<Category> set = new HashSet<>();
//        Category category1 = new Category();
//        category1.setName("vvv");
//        set.add(category1);
//        dw.setUrl("///");
//        dw.setCategories(set);
//        defaultWallpaperService.save(dw);

        return user;
    }

    @RequestMapping(value = "/addWallpToCollection", method = RequestMethod.POST)
    public User addWallpToCollection(@RequestBody UsersWalpaper usersWalpaper){
        User user = new UsersWallpaperMapper().getUSer(usersWalpaper,userService);
        CollectionWallpaper collectionWallpaper = new UsersWallpaperMapper().getWallpaper(usersWalpaper);

        collectionWallpaper = CollectionHelper.getCurrentWallpaper(collectionWallpaper, collectionWallpaperService, categoriesService);
        updateUserCollection(user, collectionWallpaper);
        return user;
    }

    @RequestMapping(value = "/addDefault", method = RequestMethod.POST)
    public String addNewWallp(@RequestBody List<DefaultWallpaper> defaultWallpapers){
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

    private void updateUserCollection(User user, CollectionWallpaper collectionWallpaper){
        Set<CollectionWallpaper> usersCollection = user.getCollectionWallpapers();
        usersCollection.add(collectionWallpaper);
        user.setCollectionWallpapers(usersCollection);
        userService.update(user);
    }
}
