package by.s0mmelier.controllers;

import by.s0mmelier.Dto.FindDto;
import by.s0mmelier.service.FindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "https://i-course.herokuapp.com", maxAge = 3600)
@RestController
@RequestMapping("/api/find")
public class FindController {

    @Autowired
    FindService findService;

    @GetMapping("/{word}")
    public FindDto getFindResult(@PathVariable("word") String tag){
        return findService.getItemsByTag(tag);
    }
}
