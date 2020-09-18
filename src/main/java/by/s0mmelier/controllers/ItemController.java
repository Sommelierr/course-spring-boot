package by.s0mmelier.controllers;

import by.s0mmelier.models.Tag;
import by.s0mmelier.service.*;
import by.s0mmelier.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ItemController {

    @Autowired
    BookCollectionService bookCollectionService;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    BookService bookService;

    @Autowired
    AlcoholService alcoholService;

    @Autowired
    MarkService markService;

    @Autowired
    TagService tagService;

    @GetMapping("/tags")
    public List<String> getAllTags(){
        List<String> tags = new ArrayList<>();
        for(Tag tag : tagRepository.findAll()){
            tags.add(tag.getName());
        }
        return tags;
    }

    @GetMapping("{collectionType}/{itemId}")
    public List<String> getItemTags(@PathVariable("collectionType") String collectionType,
                            @PathVariable("itemId") long itemId){
        if(collectionType.equals("bc")) return
                tagService.tagsToStringList(bookService.getBook(itemId).get().getTags());
        if(collectionType.equals("ac")) return
                tagService.tagsToStringList(alcoholService.getAlcohol(itemId).get().getTags());
        else return null;
    }
}
