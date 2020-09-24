package by.s0mmelier.controllers;

import by.s0mmelier.Dto.FindDto;
import by.s0mmelier.models.Tag;
import by.s0mmelier.service.AlcoholService;
import by.s0mmelier.service.BookService;
import by.s0mmelier.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/find")
public class FindController {

    @Autowired
    TagService tagService;

    @Autowired
    AlcoholService alcoholService;

    @Autowired
    BookService bookService;

    @GetMapping("/{word}")
    public FindDto getFindResult(@PathVariable("word") String word){
        System.out.println("kek");
        FindDto findDto = new FindDto();
        findDto.setAlcohols(alcoholService.getAlcoholsByTag(word));
        findDto.setBooks(bookService.getBooksByTag(word));
        return findDto;
    }
}
