package controller;

import dao.interfaces.CollectionWallpapersDao;
import dao.interfaces.DefaultWallpapersDao;
import model.Category;
import model.CollectionWallpaper;
import model.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.dto.UsersWalpaper;
import service.interfaces.CategoriesService;
import service.interfaces.CollectionWallpaperService;
import service.interfaces.UserService;
import service.mapper.UsersWallpaperMapper;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/wallpapers")
public class DefaultWallpaperController {

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("springConfig.xml");
    DefaultWallpapersDao defaultWallpapersDao = context.getBean(DefaultWallpapersDao.class);
    UserService userService = context.getBean(UserService.class);
    CollectionWallpaperService collectionWallpaperService = context.getBean(CollectionWallpaperService.class);
    CategoriesService categoriesService = context.getBean(CategoriesService.class);

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public User print(@Valid @RequestBody UsersWalpaper usersWalpaper){
        User user = new User();
        user = UsersWallpaperMapper.getUSer(usersWalpaper);
        CollectionWallpaper collectionWallpaper = UsersWallpaperMapper.getWallpaper(usersWalpaper);

        boolean isExist = false;
        List<CollectionWallpaper> collectionWallpapers = new ArrayList<>();
        collectionWallpapers = collectionWallpaperService.getAllWallpapers();
        for (CollectionWallpaper cw : collectionWallpapers){
            if (cw.equals(collectionWallpaper)) {
                collectionWallpaper = cw;
                isExist = true;
            }
        }

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
}
