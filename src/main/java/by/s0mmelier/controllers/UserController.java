package by.s0mmelier.controllers;

import by.s0mmelier.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test/user")
public class UserController {

    @Autowired
    UserService userService;

//    @GetMapping("/{id}")
//    public User user(@PathVariable("id") long id){
//        return userService.getUserById(id);
//    }

}