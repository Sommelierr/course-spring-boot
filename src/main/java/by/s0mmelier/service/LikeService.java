package by.s0mmelier.service;

import by.s0mmelier.Dto.LikeStatusDto;
import by.s0mmelier.models.Alcohol;
import by.s0mmelier.models.Book;
import by.s0mmelier.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService {

    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    @Autowired
    AlcoholService alcoholService;

    public void likeBook(long itemId, long userId){
        Optional<Book> book = bookService.getBook(itemId);
        User user = userService.getUserById(userId);
        user.getLikeBooks().add(book.get());
        book.get().getUsersLike().add(user);
        bookService.saveBook(book.get());
        userService.saveUser(user);
    }

    public void likeAlcohol(long itemId, long userId){
        Optional<Alcohol> alcohol = alcoholService.getAlcohol(itemId);
        User user = userService.getUserById(userId);
        user.getLikeAlcohols().add(alcohol.get());
        alcohol.get().getUsersLike().add(user);
        alcoholService.saveAlcohol(alcohol.get());
        userService.saveUser(user);
    }

    public LikeStatusDto getBookLikeStatus(long itemId, long userId){
        LikeStatusDto likeStatusDto = new LikeStatusDto();
        Optional<Book> book = bookService.getBook(itemId);
        for (User user : book.get().getUsersLike()) if (userId == user.getId()) likeStatusDto.setStatus(true);
        return likeStatusDto;
    }

    public LikeStatusDto getAlcoholLikeStatus(long itemId, long userId){
        LikeStatusDto likeStatusDto = new LikeStatusDto();
        Optional<Alcohol> alcohol = alcoholService.getAlcohol(itemId);
        for(User user : alcohol.get().getUsersLike()) if(userId == user.getId()) likeStatusDto.setStatus(true);
        return likeStatusDto;
    }

    public void unlikeBook(long itemId, long userId){
        Optional<Book> book = bookService.getBook(itemId);
        User user = userService.getUserById(userId);
        book.get().getUsersLike().remove(user);
        user.getLikeBooks().remove(book.get());
        bookService.saveBook(book.get());
        userService.saveUser(user);
    }

    public void unlikeAlcohol(long itemId, long userId){
        Optional<Alcohol> alcohol = alcoholService.getAlcohol(itemId);
        User user = userService.getUserById(userId);
        alcohol.get().getUsersLike().remove(user);
        user.getLikeAlcohols().remove(alcohol.get());
        alcoholService.saveAlcohol(alcohol.get());
        userService.saveUser(user);
    }
}
