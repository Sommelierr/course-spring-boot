package by.s0mmelier.service;

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
        System.out.println(book.toString());
        bookCollectionRepository.save(bookCollection);
    }

    public void deleteBookCollection(long id){
        bookCollectionRepository.deleteById(id);
    }
}
