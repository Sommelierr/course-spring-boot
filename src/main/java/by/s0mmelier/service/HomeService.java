package by.s0mmelier.service;

import by.s0mmelier.Dto.HomeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeService {
    @Autowired
    TagService tagService;

    @Autowired
    BookService bookService;

    @Autowired
    CollectionService collectionService;

    @Autowired
    AlcoholService alcoholService;
    public HomeDto getHomeData(){
        return null;
    }
}
