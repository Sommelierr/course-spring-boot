package by.s0mmelier.service;

import by.s0mmelier.payload.request.CollectionRequest;
import by.s0mmelier.collections.MarkCollection;
import by.s0mmelier.repository.MarkCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MarkCollectionService {

    @Autowired
    MarkCollectionRepository markCollectionRepository;

    @Autowired
    ThemeService themeService;

    @Autowired
    ImageService imageService;

    public MarkCollection getMarkCollection(long id){
        return markCollectionRepository.findById(id);
    }

    public MarkCollection createCollection(CollectionRequest collectionRequest) throws IOException {
        MarkCollection markCollection = new MarkCollection();
        markCollection.setName(collectionRequest.getName());
        markCollection.setDescription(collectionRequest.getDescription());
        markCollection.setTheme(themeService.getByName(collectionRequest.getTheme()));
        markCollection.setImage(imageService.getByUrl(imageService.convertToImage(collectionRequest).getUrl()));
        markCollectionRepository.save(markCollection);
        return markCollection;
    }

    public MarkCollection updateCollection(CollectionRequest collectionRequest, long collectionId) throws IOException {
        MarkCollection markCollection = getMarkCollection(collectionId);
        markCollection.setName(collectionRequest.getName());
        markCollection.setDescription(collectionRequest.getDescription());
        markCollection.setImage(imageService.getByUrl(imageService.convertToImage(collectionRequest).getUrl()));
        markCollectionRepository.save(markCollection);
        return markCollection;
    }

    public void setBitMask(long bitMask, long collectionId){
        MarkCollection markCollection = markCollectionRepository.findById(collectionId);
        markCollection.setBitMask(bitMask);
        markCollectionRepository.save(markCollection);
    }
}
