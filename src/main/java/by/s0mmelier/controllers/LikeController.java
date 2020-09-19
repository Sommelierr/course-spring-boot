package by.s0mmelier.controllers;

import by.s0mmelier.Dto.LikeStatusDto;
import by.s0mmelier.models.Alcohol;
import by.s0mmelier.models.Book;
import by.s0mmelier.models.User;
import by.s0mmelier.service.AlcoholService;
import by.s0mmelier.service.BookService;
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
    BookService bookService;

    @Autowired
    AlcoholService alcoholService;

    @PostMapping("/like")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void like(@PathVariable("userId") long userId,
                        @PathVariable("collectionType") String collectionType,
                        @PathVariable("itemId") long itemId){
        if(collectionType.equals("bc")) {
            Optional<Book> book = bookService.getBook(itemId);
            User user = userService.getUserById(userId);
            book.get().getLikes().add(user);
            bookService.saveBook(book.get());
        }
        if(collectionType.equals("ac")){
            Optional<Alcohol> alcohol = alcoholService.getAlcohol(itemId);
            User user = userService.getUserById(userId);
            alcohol.get().getLikes().add(user);
            alcoholService.saveAlcohol(alcohol.get());
        }
    }

    @GetMapping("/like")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public LikeStatusDto getLikeStatus(@PathVariable("userId") long userId,
                                       @PathVariable("collectionType") String collectionType,
                                       @PathVariable("itemId") long itemId) {
        LikeStatusDto likeStatusDto = new LikeStatusDto();
        if(collectionType.equals("bc")){
        Optional<Book> book = bookService.getBook(itemId);
        for(User user : book.get().getLikes()) if(userId == user.getId()) likeStatusDto.setStatus(true);
        }
        if(collectionType.equals("ac")){
            Optional<Alcohol> alcohol = alcoholService.getAlcohol(itemId);
            for(User user : alcohol.get().getLikes()) if(userId == user.getId()) likeStatusDto.setStatus(true);
        }
        return likeStatusDto;
    }

    @PostMapping("/unlike")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void unlike(@PathVariable("userId") long userId,
                        @PathVariable("collectionType") String collectionType,
                        @PathVariable("itemId") long itemId){
        if(collectionType.equals("bc")) {
            Optional<Book> book = bookService.getBook(itemId);
            User user = userService.getUserById(userId);
            book.get().getLikes().remove(user);
            bookService.saveBook(book.get());
        }
        if(collectionType.equals("ac")){
            Optional<Alcohol> alcohol = alcoholService.getAlcohol(itemId);
            User user = userService.getUserById(userId);
            alcohol.get().getLikes().remove(user);
            alcoholService.saveAlcohol(alcohol.get());
        }
    }

}
