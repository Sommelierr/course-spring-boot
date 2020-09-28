package by.s0mmelier.service;

import by.s0mmelier.Dto.UserDto;
import by.s0mmelier.models.Role;
import by.s0mmelier.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    public List<UserDto> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return userService.convertToUsersDto(users);
    }

    public void block(List<Long> usersId){
        for(long id : usersId){
            User user = userService.getUserById(id);
            user.setBlocked(true);
            userService.saveUser(user);
        }
    }

    public void unblock(List<Long> usersId){
        for(long id : usersId){
            User user = userService.getUserById(id);
            user.setBlocked(false);
            userService.saveUser(user);
        }
    }

    public void setRoleAdmin(List<Long> usersId){
        Role roleAdmin = roleService.getRoleAdmin();
        for(long id : usersId){
            User user = userService.getUserById(id);
            user.getRoles().add(roleAdmin);
            userService.saveUser(user);
        }
    }

    public void delete(List<Long> usersId){
        for(long id : usersId){
            userService.deleteUserById(id);
        }
    }
}
