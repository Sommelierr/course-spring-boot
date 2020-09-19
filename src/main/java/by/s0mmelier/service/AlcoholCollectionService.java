package by.s0mmelier.service;

import by.s0mmelier.Dto.AlcoholCollectionDto;
import by.s0mmelier.Dto.BookCollectionDto;
import by.s0mmelier.payload.request.CollectionRequest;
import by.s0mmelier.collections.AlcoholCollection;
import by.s0mmelier.models.Alcohol;
import by.s0mmelier.repository.AlcoholCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AlcoholCollectionService {

    @Autowired
    AlcoholCollectionRepository alcoholCollectionRepository;

    @Autowired
    ThemeService themeService;

    @Autowired
    ImageService imageService;

    public AlcoholCollection getAlcoholCollection(long id){
        return alcoholCollectionRepository.findById(id);
    }

    public AlcoholCollection createCollection(CollectionRequest collectionRequest) throws IOException {
        AlcoholCollection alcoholCollection = new AlcoholCollection();
        alcoholCollection.setName(collectionRequest.getName());
        alcoholCollection.setDescription(collectionRequest.getDescription());
        alcoholCollection.setTheme(themeService.getByName(collectionRequest.getTheme()));
        alcoholCollection.setImage(imageService.getByUrl(imageService.convertToImage(collectionRequest).getUrl()));
        alcoholCollectionRepository.save(alcoholCollection);
        return alcoholCollection;
    }

    public AlcoholCollection updateCollection(CollectionRequest collectionRequest, long collectionId) throws IOException {
        AlcoholCollection alcoholCollection = getAlcoholCollection(collectionId);
        alcoholCollection.setName(collectionRequest.getName());
        alcoholCollection.setDescription(collectionRequest.getDescription());
        alcoholCollection.setImage(imageService.getByUrl(imageService.convertToImage(collectionRequest).getUrl()));
        alcoholCollectionRepository.save(alcoholCollection);
        return alcoholCollection;
    }

    public void setBitMask(long bitMask, long collectionId){
        AlcoholCollection alcoholCollection =alcoholCollectionRepository.findById(collectionId);
        alcoholCollection.setBitMask(bitMask);
        alcoholCollectionRepository.save(alcoholCollection);

    }

    public void saveCollection(AlcoholCollection alcoholCollection){
        alcoholCollectionRepository.save(alcoholCollection);
    }

    public void addAlcohol(long collectionId, Alcohol alcohol){
        AlcoholCollection alcoholCollection = getAlcoholCollection(collectionId);
        alcoholCollection.getAlcohols().add(alcohol);
        alcoholCollectionRepository.save(alcoholCollection);
    }

    public void deleteAlcoholCollection(long id){
        alcoholCollectionRepository.deleteById(id);
    }

    public AlcoholCollectionDto getAlcoholCollectionDto(long collectionId){
        AlcoholCollection collection = getAlcoholCollection(collectionId);
        AlcoholCollectionDto collectionDto = new AlcoholCollectionDto();
        collectionDto.setName(collection.getName());
        collectionDto.setBitMask(collection.getBitMask());
        collectionDto.setImage(collection.getImage());
        collectionDto.setDescription(collection.getDescription());
        collectionDto.setAlcohols(collection.getAlcohols());
        collectionDto.setBlocked(collection.getUser().isBlocked());
        return collectionDto;
    }
}
