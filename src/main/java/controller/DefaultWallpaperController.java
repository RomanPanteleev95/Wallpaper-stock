package controller;

import dao.interfaces.CollectionWallpapersDao;
import dao.interfaces.DefaultWallpapersDao;
import model.CollectionWallpaper;
import model.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.dto.UsersWalpaper;
import service.interfaces.UserService;
import service.mapper.UsersWallpaperMapper;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/wallpapers")
public class DefaultWallpaperController {

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("springConfig.xml");
    DefaultWallpapersDao defaultWallpapersDao = context.getBean(DefaultWallpapersDao.class);
    UserService userService = context.getBean(UserService.class);
    //дописать сервер
    CollectionWallpapersDao collectionWallpapersDao = context.getBean(CollectionWallpapersDao.class);

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public User print(@Valid @RequestBody UsersWalpaper usersWalpaper){
        User user = new User();
        user = UsersWallpaperMapper.getUSer(usersWalpaper);
        CollectionWallpaper collectionWallpaper = UsersWallpaperMapper.getWallpaper(usersWalpaper);

        List<CollectionWallpaper> collectionWallpapers = new ArrayList<>();
        collectionWallpapers = collectionWallpapersDao.getAllWallpapers();
        for (CollectionWallpaper cw : collectionWallpapers){
            if (cw.equals(collectionWallpaper))
                collectionWallpaper = cw;
        }
        Set<CollectionWallpaper> set = user.getCollectionWallpapers();
        set.add(collectionWallpaper);
        user.setCollectionWallpapers(set);
        userService.update(user);
        //добавить проверку категорий


        return user;
    }
}
