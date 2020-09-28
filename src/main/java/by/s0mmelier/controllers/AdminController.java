package by.s0mmelier.controllers;

import by.s0mmelier.Dto.UserDto;
import by.s0mmelier.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://i-course.herokuapp.com", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDto> getAllUsers(){
        return adminService.getAllUsers();
    }

    @PostMapping("/block")
    @PreAuthorize("hasRole('ADMIN')")
    public void block(@RequestParam("usersId") List<Long> usersId){
        adminService.block(usersId);
    }

    @PostMapping("/unblock")
    @PreAuthorize("hasRole('ADMIN')")
    public void unblock(@RequestParam("usersId") List<Long> usersId){
        adminService.unblock(usersId);
    }

    @PostMapping("/setRoleAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public void setRoleAdmin(@RequestParam("usersId") List<Long> usersId){
        adminService.setRoleAdmin(usersId);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@RequestParam("usersId") List<Long> usersId){
        adminService.delete(usersId);
    }
}