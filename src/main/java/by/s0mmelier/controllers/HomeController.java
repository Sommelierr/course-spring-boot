package by.s0mmelier.controllers;

import by.s0mmelier.Dto.HomeDto;
import by.s0mmelier.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "https://i-course.herokuapp.com", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    HomeService homeService;

    @Autowired
    AlcoholService alcoholService;

    @Autowired
    BookService bookService;

    @Autowired
    CollectionService collectionService;

    @Autowired
    TagService tagService;

    @GetMapping("/home")
    public HomeDto getHome(){
        HomeDto homeData = new HomeDto();
        homeData.setAlcohol(alcoholService.getLast());
        homeData.setBook(bookService.getLast());
        homeData.setTags(tagService.toCloudTags(tagService.getAlltags()));
        homeData.setAlcoholCollection(collectionService.getBiggestAlcoholHomeCollection());
        homeData.setBookCollection(collectionService.getBiggestBookHomeCollection());
        return homeData;
    }
}