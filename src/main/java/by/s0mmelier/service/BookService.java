package by.s0mmelier.service;

import by.s0mmelier.Dto.BookDto;
import by.s0mmelier.Dto.HomeAlcoholDto;
import by.s0mmelier.Dto.HomeBookDto;
import by.s0mmelier.collections.BookCollection;
import by.s0mmelier.models.Alcohol;
import by.s0mmelier.models.Book;
import by.s0mmelier.payload.request.BookRequest;
import by.s0mmelier.repository.BookRepository;
import by.s0mmelier.utils.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    TagService tagService;

    @Autowired
    UtilService utilService;

    @Autowired
    BookCollectionService bookCollectionService;


    public Optional<Book> getBook(long id){
        return bookRepository.findById(id);
    }

    public boolean createBook(long collectionId, BookRequest bookModel) throws ParseException {
        Book book = new Book();
        book.setName(bookModel.getName());
        book.setTags(tagService.toTagList(bookModel.getTags()));
        book.setCost(bookModel.getCost());
        book.setCountOfPages(bookModel.getCountOfPages());
        book.setWeight(bookModel.getWeight());
        book.setAuthor(bookModel.getAuthor());
        book.setGenre(bookModel.getGenre());
        book.setPublisher(bookModel.getPublisher());
        book.setItSerial(bookModel.isItSerial());
        book.setHasAudio(bookModel.isHasAudio());
        book.setHasFilm(bookModel.isHasFilm());
        book.setPublisher(bookModel.getPublisher());
        book.setComment(bookModel.getComment());
        book.setSummary(bookModel.getSummary());
        book.setRecommendation(bookModel.getRecommendation());
        book.setPublishingDateOnEnglish(utilService.stringToDate(bookModel.getPublishingDateOnEnglish()));
        book.setPublishingDateOnJapan(utilService.stringToDate(bookModel.getPublishingDateOnJapan()));
        book.setPublishingDateOnRussian(utilService.stringToDate(bookModel.getPublishingDateOnRussian()));
        book.setBitMask(bookCollectionService.getBookCollection(collectionId).getBitMask());
        bookRepository.save(book);
        bookCollectionService.addBook(collectionId, book);
        return true;
    }

    public boolean updateBook(long bookId, BookRequest bookModel) throws ParseException {
        Optional<Book> book = bookRepository.findById(bookId);
        book.get().setName(bookModel.getName());
        book.get().setTags(tagService.toTagList(bookModel.getTags()));
        book.get().setCost(bookModel.getCost());
        book.get().setCountOfPages(bookModel.getCountOfPages());
        book.get().setWeight(bookModel.getWeight());
        book.get().setAuthor(bookModel.getAuthor());
        book.get().setGenre(bookModel.getGenre());
        book.get().setItSerial(bookModel.isItSerial());
        book.get().setHasAudio(bookModel.isHasAudio());
        book.get().setHasFilm(bookModel.isHasFilm());
        book.get().setPublisher(bookModel.getPublisher());
        book.get().setComment(bookModel.getComment());
        book.get().setSummary(bookModel.getSummary());
        book.get().setRecommendation(bookModel.getRecommendation());
        book.get().setPublishingDateOnEnglish(utilService.stringToDate(bookModel.getPublishingDateOnEnglish()));
        book.get().setPublishingDateOnJapan((utilService.stringToDate(bookModel.getPublishingDateOnJapan())));
        book.get().setPublishingDateOnRussian(utilService.stringToDate(bookModel.getPublishingDateOnRussian()));
        bookRepository.save(book.get());
        return true;
    }

    public void saveBook(Book book){
        bookRepository.save(book);
    }

    public void deleteBook(long bookId){
        BookCollection collection =bookCollectionService.getBookCollection(bookRepository.findById(bookId)
                                                        .get().getBookCollection().getId());
        collection.setCountOfBooks(collection.getCountOfBooks()-1);
        bookCollectionService.saveCollection(collection);
        bookRepository.deleteById(bookId);
    }

    public BookDto getBookDto(long id){
        Optional<Book> book = bookRepository.findById(id);
        BookDto bookDto = new BookDto();
        bookDto.setName(book.get().getName());
        bookDto.setTags(tagService.tagsToStringList(book.get().getTags()));
        bookDto.setCost(book.get().getCost());
        bookDto.setCountOfPages(book.get().getCountOfPages());
        bookDto.setWeight(book.get().getWeight());
        bookDto.setAuthor(book.get().getAuthor());
        bookDto.setGenre(book.get().getGenre());
        bookDto.setPublisher(book.get().getPublisher());
        bookDto.setItSerial(book.get().isItSerial());
        bookDto.setHasAudio(book.get().isHasAudio());
        bookDto.setHasFilm(book.get().isHasFilm());
        bookDto.setComment(book.get().getComment());
        bookDto.setSummary(book.get().getSummary());
        bookDto.setRecommendation(book.get().getRecommendation());
        bookDto.setPublishingDateOnEnglish(book.get().getPublishingDateOnEnglish());
        bookDto.setPublishingDateOnRussian(book.get().getPublishingDateOnRussian());
        bookDto.setPublishingDateOnJapan(book.get().getPublishingDateOnJapan());
        bookDto.setBitMask(book.get().getBitMask());
        return bookDto;
    }

    public HomeBookDto getLast(){
        if(bookRepository.findTopByOrderByIdDesc() != null) {
            Book book = bookRepository.findTopByOrderByIdDesc();
            HomeBookDto bookDto = new HomeBookDto();
            bookDto.setId(book.getId());
            bookDto.setName(book.getName());
            return bookDto;
        }
        else return null;
    }
}