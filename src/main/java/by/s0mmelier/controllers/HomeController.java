package by.s0mmelier.controllers;

import by.s0mmelier.Dto.HomeCollectionDto;
import by.s0mmelier.Dto.HomeDto;
import by.s0mmelier.service.AlcoholService;
import by.s0mmelier.service.BookService;
import by.s0mmelier.service.CollectionService;
import by.s0mmelier.service.TagService;
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
    BookService bookService;

    @Autowired
    CollectionService collectionService;

    @Autowired
    AlcoholService alcoholService;

    @Autowired
    TagService tagService;

    @GetMapping("/home")
    public HomeDto getHome(){
        HomeDto homeData = new HomeDto();
        homeData.setAlcohol(alcoholService.getLast());
        homeData.setBook(bookService.getLast());
        homeData.setTags(tagService.tagsToStringList(tagService.getAlltags()));
        //homeData.setCollection(collectionService.getBiggestCollectiion());
        return homeData;
    }
}