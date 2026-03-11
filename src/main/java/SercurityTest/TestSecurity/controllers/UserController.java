package SercurityTest.TestSecurity.controllers;

import SercurityTest.TestSecurity.configuration.SecurityConfig;
import SercurityTest.TestSecurity.dao.LoginRequestDao;
import SercurityTest.TestSecurity.dao.UserRequestDao;
import SercurityTest.TestSecurity.dao.UserResponseDao;
import SercurityTest.TestSecurity.entity.UserEntity;
import SercurityTest.TestSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    SecurityConfig securityConfig;
    @GetMapping("/getalluser")
    public   List<UserEntity> getAllUser(){
       return  userService.getAllUser();
    }

    @PostMapping("/newuser")
    public  String newUser(@RequestBody UserRequestDao   userRequestDao){
     userRequestDao.setPassword(securityConfig.passwordEncoder().encode(userRequestDao.getPassword()));
      return  userService.newUser(userRequestDao);
    }

    @PostMapping("/login")
    public  String login(@RequestBody LoginRequestDao loginRequestDao){
        return userService.login(loginRequestDao);

    }

    @GetMapping("/getone/{username}")
    public UserResponseDao getOne(@PathVariable String username){
        return  userService.getResponse(username);
    }
}
