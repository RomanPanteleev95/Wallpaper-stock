package com.controller;

import com.model.User;
import com.model.CollectionWallpaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.service.interfaces.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String userRegistration(@Valid @RequestBody User user){
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

    @RequestMapping(value = "/passwordChange", method = RequestMethod.POST)
    public String passwordChange(@Valid @RequestBody User user){

        return null;
    }

    @RequestMapping(value = "/images", method = RequestMethod.POST)
    public List<CollectionWallpaper> getUsersWallpapers(){
        return userService.getAllImage();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String Login(@Valid @RequestBody User user){
        return null;
    }

    public String Logout(@RequestBody User user){return null;}

    @RequestMapping(value = "/lastActiveTimeUpdate", method = RequestMethod.POST)
    public String lastActiveTimeUpdate(@Valid @RequestBody User user){
        return null;
    }
}
