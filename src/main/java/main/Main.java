package main;

import dao.interfaces.CollectionWallpapersDao;
import model.Category;
import model.CollectionWallpaper;
import model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.dto.UsersWalpaper;
import service.interfaces.UserService;
import service.mapper.UsersWallpaperMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ComponentScan(value = "controller")
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("springConfig.xml");
//        SpringApplication.run(Main.class, args);
        UserService userService = context.getBean(UserService.class);
        CollectionWallpapersDao collectionWallpapersDao = context.getBean(CollectionWallpapersDao.class);

        User user = new User();
        user = userService.getUserByLogin("loglog");
        CollectionWallpaper collectionWallpaper = new CollectionWallpaper();
        collectionWallpaper.setUrl("addWalp");
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





        context.close();

    }
}
