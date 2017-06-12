package controller;

import model.User;
import model.CollectionWallpaper;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.interfaces.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("springConfig.xml");
    UserService userService = context.getBean(UserService.class);


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String userRegistration(@Valid @RequestBody User user){
//        try{
//            user.getLogin().equals("");
//        }catch (NullPointerException e){
//            return "Error: enter login!";
//        }
//
//        try{
//            user.getNewPassword().equals("");
//        }catch (NullPointerException e){
//            return "Error: enter password";
//        }

        if (user.getLogin().length() < 1)
            return "Error: enter login!";

        if (user.getNewPassword().length() < 1)
            return "Error: enter password!";


        user.setOldPassword(user.getNewPassword());
        user.setSessionId(-1);
        user.setLastActiveTime("not active yet");
        List<User> users = userService.getAllUsers();
        if (!users.contains(user)) {
            userService.saveUser(user);
            return "Registration COMPLETE!";
        }else
            return "Error: login already exist!";
    }

    @RequestMapping(value = "/images")
    public List<CollectionWallpaper> getUsersWallpapers(){
        return userService.getAllImage();
    }
}
