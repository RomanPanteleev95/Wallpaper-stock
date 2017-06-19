package com.controller;

import com.model.User;
import com.model.CollectionWallpaper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.service.interfaces.UserService;

import javax.validation.Valid;
import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value = "UsersControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ApiOperation("User's registration.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "COMPLETE", response = User.class)})
    public String userRegistration(@RequestBody User user){
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
    @ApiOperation("Change user's password")
    @ApiResponse(code = 200, message = "COMPLETE", response = User.class)
    public String passwordChange(@RequestBody User user){
        User currentUser = userService.getUserByLogin(user.getLogin());
        currentUser.setOldPassword(currentUser.getNewPassword());
        currentUser.setNewPassword(user.getNewPassword());
        userService.update(currentUser);
        return "OK!";
    }

    @RequestMapping(value = "/images", method = RequestMethod.POST)
    public List<CollectionWallpaper> getUsersWallpapers(){
        return userService.getAllImage();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String Login(@RequestBody User user){
        User currentUser = userService.getUserByLogin(user.getLogin());
        currentUser.setSessionId(user.getSessionId());
        currentUser.setLastActiveTime(user.getLastActiveTime());
        userService.update(currentUser);
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String Logout(@RequestBody User user){
        User currentUser = userService.getUserByLogin(user.getLogin());
        currentUser.setSessionId(-1);
        userService.update(currentUser);
        return "logout";
    }

    @RequestMapping(value = "/lastActiveTimeUpdate", method = RequestMethod.POST)
    public String lastActiveTimeUpdate(@RequestBody User user){
        User currentUser = userService.getUserByLogin(user.getLogin());
        currentUser.setLastActiveTime(user.getLastActiveTime());
        userService.update(currentUser);
        return "LAT update";
    }
}
