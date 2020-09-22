package by.s0mmelier.controllers;

import by.s0mmelier.Dto.UserDto;
import by.s0mmelier.models.Role;
import by.s0mmelier.models.User;
import by.s0mmelier.service.RoleService;
import by.s0mmelier.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @GetMapping("/users")
    public List<UserDto> userDtos(){
        List<User> users = userService.getAllUsers();
        return userService.convertToUsersDto(users);
    }

    @PostMapping("/block")
    public void block(@RequestParam("usersId") List<Long> usersId){
        for(long id : usersId){
            User user = userService.getUserById(id);
            user.setBlocked(true);
            userService.saveUser(user);
        }
    }

    @PostMapping("/unblock")
    public void unblock(@RequestParam("usersId") List<Long> usersId){
        for(long id : usersId){
            User user = userService.getUserById(id);
            user.setBlocked(false);
            userService.saveUser(user);
        }
    }

    @PostMapping("/setRoleAdmin")
    public void setRoleAdmin(@RequestParam("usersId") List<Long> usersId){
        Role roleAdmin = roleService.getRoleAdmin();
        for(long id : usersId){
            User user = userService.getUserById(id);
            user.getRoles().add(roleAdmin);
            userService.saveUser(user);
        }
    }

    @PostMapping("/delete")
    public void delete(@RequestParam("usersId") List<Long> usersId){
        for(long id : usersId){
            userService.deleteUserById(id);
        }
    }



}