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
import service.interfaces.CategoriesService;
import service.interfaces.CollectionWallpaperService;
import service.interfaces.UserService;
import service.mapper.UsersWallpaperMapper;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ComponentScan(value = "controller")
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("output.txt")));
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("springConfig.xml");
        SpringApplication.run(Main.class, args);
//
//        UserService userService = context.getBean(UserService.class);
//
//        User user = new User();
//
//        user.setLogin("Annotation");
//
//        userService.saveUser(user);
//
//        context.close();
//        out.close();
    }
}
