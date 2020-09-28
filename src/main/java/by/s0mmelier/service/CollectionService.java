package by.s0mmelier.service;

import by.s0mmelier.Dto.CollectionBitMaskDto;
import by.s0mmelier.Dto.CollectionDto;
import by.s0mmelier.Dto.HomeCollectionDto;
import by.s0mmelier.collections.AlcoholCollection;
import by.s0mmelier.collections.BookCollection;
import by.s0mmelier.collections.Collection;
import by.s0mmelier.models.Alcohol;
import by.s0mmelier.models.Theme;
import by.s0mmelier.payload.request.CollectionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@Service
public class CollectionService {
    @Autowired
    ThemeService themeService;

    @Autowired
    AlcoholCollectionService alcoholCollectionService;

    @Autowired
    BookCollectionService bookCollectionService;

    @Autowired
    UserService userService;

    public void initThems(){
        initBookTheme();
        initAlcoholTheme();
    }

    public void initBookTheme(){
        if(!themeService.existsByName("Books")){
            Theme themeBooks = new Theme();
            themeBooks.setName("Books");
            themeService.save(themeBooks);
        }
    }

    public void initAlcoholTheme(){
        if(!themeService.existsByName("Alcohol")){
            Theme themeAlcohol = new Theme();
            themeAlcohol.setName("Alcohol");
            themeService.save(themeAlcohol);
        }
    }

    public HomeCollectionDto getBiggestAlcoholHomeCollection(){
        AlcoholCollection alcoholCollection = alcoholCollectionService.getBiggestAlcoholCollection();
        if(alcoholCollection != null) return convertToHomeCollectionDto(alcoholCollection);
        else return null;
    }

    public HomeCollectionDto getBiggestBookHomeCollection(){
        BookCollection bookCollection = bookCollectionService.getBiggestBookCollection();
        if(bookCollection != null) return convertToHomeCollectionDto(bookCollection);
        else return null;
    }

    public HomeCollectionDto convertToHomeCollectionDto(BookCollection collection){
            HomeCollectionDto homeCollection = new HomeCollectionDto();
            homeCollection.setId(collection.getId());
            homeCollection.setName(collection.getName());
            homeCollection.setTheme(collection.getTheme().getName());
            homeCollection.setUserId(collection.getUser().getId());
            return homeCollection;
    }

    public HomeCollectionDto convertToHomeCollectionDto(AlcoholCollection collection){
            HomeCollectionDto homeCollection = new HomeCollectionDto();
            homeCollection.setId(collection.getId());
            homeCollection.setName(collection.getName());
            homeCollection.setTheme(collection.getTheme().getName());
            homeCollection.setUserId(collection.getUser().getId());
            return homeCollection;
    }

    public void createCollection(long userId, CollectionRequest collectionRequest) throws IOException {
        if(collectionRequest.getTheme().equals("Books")){
            userService.addBookCollection(userId, bookCollectionService.createCollection(collectionRequest)); }
        if(collectionRequest.getTheme().equals("Alcohol")) {
            userService.addAlcoholCollection(userId, alcoholCollectionService.createCollection(collectionRequest)); }
    }

    public CollectionDto getCollectionDto(String collectionType, long collectionId){
        if(collectionType.equals("bc")) return bookCollectionService.getBookCollectionDto(collectionId);
        if(collectionType.equals("ac")) return alcoholCollectionService.getAlcoholCollectionDto(collectionId);
        else return null;
    }

    public void deleteCollection(String collectionType, long collectionId){
        if(collectionType.equals("bc")) bookCollectionService.deleteBookCollection(collectionId);
        if(collectionType.equals("ac")) alcoholCollectionService.deleteAlcoholCollection(collectionId);
    }

    public void updateCollection(String collectionType,long collectionId, CollectionRequest collectionRequest ) throws IOException {
        if(collectionType.equals("bc")){ // bc = book collection type on URL
            bookCollectionService.updateCollection(collectionRequest, collectionId); }
        if(collectionType.equals("ac")) { //bc = alcohol collection type on URL
            alcoholCollectionService.updateCollection(collectionRequest, collectionId); }
    }

    public void setBitMask(String collectionType, long collectionId, long bitMask){
        if(collectionType.equals("bc")){ // bc = book collection type on URL
            bookCollectionService.setBitMask(bitMask, collectionId); }
        if(collectionType.equals("ac")) { //bc = alcohol collection type on URL
            alcoholCollectionService.setBitMask(bitMask, collectionId); }
    }

    public CollectionBitMaskDto getBookCollectionBitMask(long collectionId){
        CollectionBitMaskDto bitMaskDto = new CollectionBitMaskDto();
        bitMaskDto.setBitMask(bookCollectionService.getBookCollection(collectionId).getBitMask());
        return bitMaskDto;
    }

    public CollectionBitMaskDto getAlcoholCollectionBitMask(long collectionId){
        CollectionBitMaskDto bitMaskDto = new CollectionBitMaskDto();
        bitMaskDto.setBitMask(alcoholCollectionService.getAlcoholCollection(collectionId).getBitMask());
        return bitMaskDto;
    }
}
