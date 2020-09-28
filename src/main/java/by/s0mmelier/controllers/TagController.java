package by.s0mmelier.controllers;

import by.s0mmelier.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "https://i-course.herokuapp.com", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TagController {

    @Autowired
    TagService tagService;

    @GetMapping("/tags")
    public List<String> getTagsNames(){
        return tagService.getTagsNames(tagService.getAlltags());
    }

    @GetMapping("{collectionType}/{itemId}")
    public List<String> getItemTags(@PathVariable("collectionType") String collectionType,
                                    @PathVariable("itemId") long itemId) {
        return tagService.getItemTagsNames(collectionType, itemId);
    }
}
