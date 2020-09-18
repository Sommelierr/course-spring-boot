package by.s0mmelier.controllers;

import by.s0mmelier.Dto.LikeStatusDto;
import by.s0mmelier.models.Book;
import by.s0mmelier.models.User;
import by.s0mmelier.service.LikeService;
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
    LikeService likeService;

    @Autowired
    BookService bookService;

    @PostMapping("/like")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void like(@PathVariable("userId") long userId,
                        @PathVariable("collectionType") String collectionType,
                        @PathVariable("itemId") long itemId){
        Optional<Book> book = bookService.getBook(itemId);
        User user = userService.getUserById(userId);
        book.get().getLikes().add(user);
        bookService.saveBook(book.get());
    }

    @GetMapping("/like")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public LikeStatusDto getLikeStatus(@PathVariable("userId") long userId,
                                       @PathVariable("collectionType") String collectionType,
                                       @PathVariable("itemId") long itemId) {
        LikeStatusDto likeStatusDto = new LikeStatusDto();
        Optional<Book> book = bookService.getBook(itemId);
        for(User user : book.get().getLikes()) if(userId == user.getId()) likeStatusDto.setStatus(true);
        return likeStatusDto;
    }

    @PostMapping("/unlike")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void unlike(@PathVariable("userId") long userId,
                        @PathVariable("collectionType") String collectionType,
                        @PathVariable("itemId") long itemId){
        Optional<Book> book = bookService.getBook(itemId);
        User user = userService.getUserById(userId);
        book.get().getLikes().remove(user);
        bookService.saveBook(book.get());
    }

}
