package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("springConfig.xml");
    UserService userService = context.getBean(UserService.class);

    @RequestMapping(value = "/users/")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
