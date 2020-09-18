package by.s0mmelier.service;

import by.s0mmelier.models.Like;
import by.s0mmelier.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    LikeRepository likeRepository;

    public void save(Like like){
        likeRepository.save(like);
    }

    public void deleteLike(long id){
        likeRepository.deleteById(id);
    }
}
