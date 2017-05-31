package main;

import dao.UserDao;
import dao.WallpaperDao;
import model.User;
import model.Wallpaper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;

import java.util.HashSet;
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
        WallpaperDao wallpaperDao = context.getBean(WallpaperDao.class);

        List<Wallpaper> wallpapers = userService.getAllImage();

        for(Wallpaper w :wallpapers)
            System.out.println(w);

        context.close();

    }
}
