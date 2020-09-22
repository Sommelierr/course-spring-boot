package by.s0mmelier.service;

import by.s0mmelier.Dto.BookCollectionDto;
import by.s0mmelier.Dto.HomeBookDto;
import by.s0mmelier.Dto.HomeCollectionDto;
import by.s0mmelier.collections.AlcoholCollection;
import by.s0mmelier.collections.BookCollection;
import by.s0mmelier.models.Book;
import by.s0mmelier.repository.BookCollectionRepository;
import by.s0mmelier.payload.request.CollectionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BookCollectionService {

    @Autowired
    BookCollectionRepository bookCollectionRepository;

    @Autowired
    ThemeService themeService;

    @Autowired
    ImageService imageService;

    public BookCollection getBookCollection(long id){
        return bookCollectionRepository.findById(id);
    }

    public BookCollection createCollection(CollectionRequest collectionRequest) throws IOException {
        BookCollection bookCollection = new BookCollection();
        bookCollection.setName(collectionRequest.getName());
        bookCollection.setDescription(collectionRequest.getDescription());
        bookCollection.setTheme(themeService.getByName(collectionRequest.getTheme()));
        bookCollection.setImage(imageService.getByUrl(imageService.convertToImage(collectionRequest).getUrl()));
        bookCollection.setCountOfBooks(0);
        bookCollectionRepository.save(bookCollection);
        return bookCollection;
    }

    public BookCollection updateCollection(CollectionRequest collectionRequest, long collectionId) throws IOException {
        BookCollection bookCollection = getBookCollection(collectionId);
        bookCollection.setName(collectionRequest.getName());
        bookCollection.setDescription(collectionRequest.getDescription());
        bookCollection.setImage(imageService.getByUrl(imageService.convertToImage(collectionRequest).getUrl()));
        bookCollectionRepository.save(bookCollection);
        return bookCollection;
    }

    public void setBitMask(long bitMask, long collectionId){
        BookCollection bookCollection = bookCollectionRepository.findById(collectionId);
        bookCollection.setBitMask(bitMask);
        bookCollectionRepository.save(bookCollection);
    }

    public void saveCollection(BookCollection bookCollection){
        bookCollectionRepository.save(bookCollection);
    }

    public void addBook(long collectionId, Book book){
        BookCollection bookCollection = getBookCollection(collectionId);
        bookCollection.getBooks().add(book);
        bookCollection.setCountOfBooks(bookCollection.getCountOfBooks()+1);
        bookCollectionRepository.save(bookCollection);
    }

    public void deleteBookCollection(long id){
        bookCollectionRepository.deleteById(id);
    }

    public BookCollectionDto getBookCollectionDto(long collectionId){
        BookCollection collection = getBookCollection(collectionId);
        BookCollectionDto collectionDto = new BookCollectionDto();
        collectionDto.setName(collection.getName());
        collectionDto.setBitMask(collection.getBitMask());
        collectionDto.setImage(collection.getImage());
        collectionDto.setDescription(collection.getDescription());
        collectionDto.setBooks(collection.getBooks());
        collectionDto.setBlocked(collection.getUser().isBlocked());
        return collectionDto;
    }

    public BookCollection getBiggestBookCollection(){
        BookCollection bookCollection = new BookCollection();
        bookCollection.setCountOfBooks(0);
        if(bookCollectionRepository.findAll() != null) {
            for (BookCollection collection : bookCollectionRepository.findAll()) {
                if (collection.getCountOfBooks() > bookCollection.getCountOfBooks()) {
                    bookCollection = collection;
                }
            }
        }
        else return null;
        return bookCollection;
    }
}
