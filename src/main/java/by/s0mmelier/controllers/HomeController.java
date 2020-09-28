package by.s0mmelier.controllers;

import by.s0mmelier.Dto.HomeDto;
import by.s0mmelier.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    HomeService homeService;

    @GetMapping("/home")
    public HomeDto getHome(){
        return homeService.getHomeData();
    }
}