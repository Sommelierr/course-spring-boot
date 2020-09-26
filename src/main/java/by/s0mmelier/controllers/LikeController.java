package by.s0mmelier.controllers;

import by.s0mmelier.Dto.LikeStatusDto;
import by.s0mmelier.models.Alcohol;
import by.s0mmelier.models.Book;
import by.s0mmelier.models.User;
import by.s0mmelier.service.AlcoholService;
import by.s0mmelier.service.BookService;
import by.s0mmelier.service.LikeService;
import by.s0mmelier.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/{userId}/{collectionType}/{itemId}")
public class LikeController {

    @Autowired
    UserService userService;

    @Autowired
    LikeService likeService;

    @Autowired
    BookService bookService;

    @Autowired
    AlcoholService alcoholService;


    @PostMapping("/like")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void like(@PathVariable("userId") long userId,
                        @PathVariable("collectionType") String collectionType,
                        @PathVariable("itemId") long itemId){
        System.out.println(userId + " " + collectionType + " " + itemId);
        if(collectionType.equals("bc")) likeService.likeBook(itemId, userId);
        if(collectionType.equals("ac")) likeService.likeAlcohol(itemId,userId);
    }

    @GetMapping("/like")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public LikeStatusDto getLikeStatus(@PathVariable("userId") long userId,
                                       @PathVariable("collectionType") String collectionType,
                                       @PathVariable("itemId") long itemId) {
        if(collectionType.equals("bc")) return likeService.getBookLikeStatus(itemId, userId);
        else  return likeService.getAlcoholLikeStatus(itemId, userId);
    }

    @PostMapping("/unlike")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void unlike(@PathVariable("userId") long userId,
                        @PathVariable("collectionType") String collectionType,
                        @PathVariable("itemId") long itemId){
        if(collectionType.equals("bc")) likeService.unlikeBook(itemId,userId);
        if(collectionType.equals("ac")) likeService.unlikeAlcohol(itemId,userId);
    }

}
