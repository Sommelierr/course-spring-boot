package by.s0mmelier.service;

import by.s0mmelier.Dto.HomeCollectionDto;
import by.s0mmelier.collections.AlcoholCollection;
import by.s0mmelier.collections.BookCollection;
import by.s0mmelier.collections.Collection;
import by.s0mmelier.models.Alcohol;
import by.s0mmelier.models.Theme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionService {
    @Autowired
    ThemeService themeService;

    @Autowired
    AlcoholCollectionService alcoholCollectionService;

    @Autowired
    BookCollectionService bookCollectionService;

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

    public HomeCollectionDto getBiggestCollectiion(){
        BookCollection bookCollection = bookCollectionService.getBiggestBookCollection();
        AlcoholCollection alcoholCollection = alcoholCollectionService.getBiggestAlcoholCollection();
        if(bookCollection == null && alcoholCollection == null) return null;
        if(bookCollection == null && alcoholCollection != null)
            return alcoholCollectionToHomeCollectionDto(alcoholCollection);
        if(bookCollection != null && alcoholCollection == null)
            return bookCollectionToHomeCollectionDto(bookCollection);
        if(bookCollection.getCountOfBooks() > alcoholCollection.getCountOfAlcohols()){
            return bookCollectionToHomeCollectionDto(bookCollection);
        }
        else return alcoholCollectionToHomeCollectionDto(alcoholCollection);
    }

    public HomeCollectionDto bookCollectionToHomeCollectionDto(BookCollection collection){
        HomeCollectionDto homeCollection = new HomeCollectionDto();
        homeCollection.setId(collection.getId());
        homeCollection.setName(collection.getName());
        homeCollection.setTheme(collection.getTheme().getName());
        return homeCollection;
    }

    public HomeCollectionDto alcoholCollectionToHomeCollectionDto(AlcoholCollection collection){
        HomeCollectionDto homeCollection = new HomeCollectionDto();
        homeCollection.setId(collection.getId());
        homeCollection.setName(collection.getName());
        homeCollection.setTheme(collection.getTheme().getName());
        return homeCollection;
    }

}
