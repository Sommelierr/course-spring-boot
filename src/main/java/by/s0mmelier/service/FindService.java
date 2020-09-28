package by.s0mmelier.service;

import by.s0mmelier.Dto.FindDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindService {

    @Autowired
    TagService tagService;

    @Autowired
    BookService bookService;

    @Autowired
    AlcoholService alcoholService;

    public FindDto getItemsByTag(String tag){
        if(tagService.getByName(tag).getAlcohols().isEmpty() && tagService.getByName(tag).getBooks().isEmpty())
            return null;
        FindDto findDto = new FindDto();
        findDto.setAlcohols(alcoholService.getAlcoholsByTag(tag));
        findDto.setBooks(bookService.getBooksByTag(tag));
        return findDto;
    }
}
