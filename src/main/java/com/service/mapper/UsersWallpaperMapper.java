package com.service.mapper;

import com.model.Category;
import com.model.CollectionWallpaper;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.service.dto.UsersWalpaper;
import com.service.interfaces.UserService;

import java.util.*;

public class UsersWallpaperMapper {
    @Autowired
    static UserService userService;
    public Pair getUserWallpapers(UsersWalpaper usersWalpaper){
        User user = userService.getUserByLogin(usersWalpaper.getLogin());
        CollectionWallpaper collectionWallpaper = new CollectionWallpaper();
        collectionWallpaper.setUrl(usersWalpaper.getUrl());
        collectionWallpaper.setWidth(usersWalpaper.getWidth());
        collectionWallpaper.setHeight(usersWalpaper.getHeight());
        List<Category> categories = new ArrayList<>();
        categories = usersWalpaper.getCategories();
        Set<Category> categorySet = new HashSet<>();
        categories.addAll(categories);
        collectionWallpaper.setCategories(categorySet);
        return new Pair(user, collectionWallpaper);
    }

    public static User getUSer(UsersWalpaper usersWalpaper){
        User user = userService.getUserByLogin(usersWalpaper.getLogin());
        return user;
    }

    public static CollectionWallpaper getWallpaper(UsersWalpaper usersWalpaper){
        CollectionWallpaper collectionWallpaper = new CollectionWallpaper();
        collectionWallpaper.setUrl(usersWalpaper.getUrl());
        collectionWallpaper.setWidth(usersWalpaper.getWidth());
        collectionWallpaper.setHeight(usersWalpaper.getHeight());
        collectionWallpaper.setAuthor(usersWalpaper.getAuthor());
        collectionWallpaper.setLikes(usersWalpaper.getLikes());

        List<Category> categories = new ArrayList<>();
        categories = usersWalpaper.getCategories();
        Set<Category> categorySet = new HashSet<>();
        for(int i = 0; i < categories.size(); i++)
            categorySet.add(categories.get(i));
//        categories.addAll(categories);
        collectionWallpaper.setCategories(categorySet);
        return collectionWallpaper;
    }
}
