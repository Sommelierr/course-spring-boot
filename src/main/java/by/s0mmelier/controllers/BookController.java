package by.s0mmelier.controllers;

import by.s0mmelier.Dto.BookDto;
import by.s0mmelier.models.Book;
import by.s0mmelier.payload.request.BookRequest;
import by.s0mmelier.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/book/{collectionId}/create")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void createBook(@PathVariable("collectionId") long collectionId,
                           @Valid @ModelAttribute BookRequest bookModel) throws Exception{
        bookService.createBook(collectionId,bookModel);
    }

    @GetMapping("book/{bookId}")
    public BookDto getBook(@PathVariable("bookId") long bookId){
        System.out.println(bookId);
        System.out.println(bookService.getBook(bookId).get().getName());
        return bookService.getBookDto(bookId);
    }

    @PostMapping("book/{bookId}/bitMask")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void setBookBitMask(@PathVariable("bookId") long bookId, @RequestParam("bitMask") long bitMask){
        if(bookService.getBook(bookId) != null) {
            Book book = bookService.getBook(bookId).get();
            book.setBitMask(bitMask);
            bookService.saveBook(book);
        }
    }

    @RequestMapping(value = "book/{bookId}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void deleteBook(@PathVariable("bookId") long bookId) {
        if(bookService.getBook(bookId) != null) {
            bookService.deleteBook(bookId);
        }
    }

    @RequestMapping(value = "book/{bookId}", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void updateBook(@PathVariable("bookId") long bookId,
                           @Valid @ModelAttribute BookRequest bookModel) throws IOException, ParseException {
        bookService.updateBook(bookId, bookModel);
    }
}
