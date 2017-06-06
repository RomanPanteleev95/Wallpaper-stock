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
import service.interfaces.UserService;
import service.mapper.UsersWallpaperMapper;

import java.util.ArrayList;
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
        CollectionWallpapersDao collectionWallpapersDao = context.getBean(CollectionWallpapersDao.class);
        CategoriesService categoriesService = context.getBean(CategoriesService.class);

        Category category = new Category();
        category.setName("Home");

        CollectionWallpaper cw = new CollectionWallpaper();
        cw.setUrl("URL_URL");
        Set<Category> categorySet = new HashSet<>();
        Category category1 = new Category();
        category1.setName("Home");
        categorySet.add(category1);

        Category category2 = new Category();
        category2.setName("doctor");
        categorySet.add(category2);

        Category category3 = new Category();
        category3.setName("jingle_bells");
        categorySet.add(category3);

        cw.setCategories(categorySet);



        Set<Category> categorySet1 = cw.getCategories();
        Set<Category> categorySet12 = new HashSet<>();
        List<Category> categoryList = categoriesService.getAllCategories();
        Category[] ctgArr = categorySet1.toArray(new Category[categorySet1.size()]);


        for (Category ctg : ctgArr){
            Category ctgTmp = ctg;
            for (Category ctgL : categoryList)
                if (ctg.equals(ctgL))
                    ctgTmp = ctgL;
            categorySet12.add(ctgTmp);
        }

        cw.setCategories(categorySet12);

        collectionWallpapersDao.save(cw);

        context.close();

    }
}
