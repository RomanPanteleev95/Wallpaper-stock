package com.service.mapper;

import com.model.Category;
import com.model.CollectionWallpaper;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.service.dto.UsersWalpaper;
import com.service.interfaces.UserService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

public class UsersWallpaperMapper {

    public Pair getUserWallpapers(UsersWalpaper usersWalpaper, UserService userService){
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

    public User getUSer(UsersWalpaper usersWalpaper, UserService userService){
        User user = userService.getUserByLogin(usersWalpaper.getLogin());
        return user;
    }

    public CollectionWallpaper getWallpaper(UsersWalpaper usersWalpaper){
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
