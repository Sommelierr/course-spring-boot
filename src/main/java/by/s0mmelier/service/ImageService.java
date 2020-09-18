package by.s0mmelier.service;


import by.s0mmelier.repository.ImageRepository;
import by.s0mmelier.models.Image;
import by.s0mmelier.payload.request.CollectionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    CloudinaryService cloudinaryService;

    public List<Image> list(){
        return imageRepository.findByOrderById();
    }

    public Optional<Image> getOne(int id){
        return imageRepository.findById(id);
    }

    public Image getByUrl(String url){
        return imageRepository.findByUrl(url);
    }

    public void save(Image image){
        imageRepository.save(image);
    }

    public void delete(int id){
        imageRepository.deleteById(id);
    }

    public boolean exists(int id){
        return imageRepository.existsById(id);
    }

    public Image convertToImage(CollectionRequest collectionRequest) throws IOException {
        BufferedImage bi = ImageIO.read(collectionRequest.getImage().getInputStream());
        Map result = cloudinaryService.upload(collectionRequest.getImage());
        Image image =
                new Image((String)result.get("original_filename"),
                        (String)result.get("url"),
                        (String)result.get("public_id"));
        save(image);
        return image;
    }
}
