package by.s0mmelier.service;

import by.s0mmelier.collections.BookCollection;
import by.s0mmelier.models.User;
import by.s0mmelier.repository.UserRepository;
import by.s0mmelier.collections.AlcoholCollection;
import by.s0mmelier.collections.MarkCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookCollectionService bookCollectionService;

    public User getUserById(long id){
        return userRepository.findById(id);
    }

    public boolean addBookCollection(long userId, BookCollection bookCollection){
        User user = userRepository.findById(userId);
        user.getBookCollections().add(bookCollection);
        userRepository.save(user);
        return true;
    }

    public boolean addAlcoholCollection(long userId, AlcoholCollection alcoholCollection){
        User user = userRepository.findById(userId);
        user.getAlcoholCollections().add(alcoholCollection);
        userRepository.save(user);
        return true;
    }

    public boolean addMarkCollection(long userId, MarkCollection markCollection){
        User user = userRepository.findById(userId);
        user.getMarkCollections().add(markCollection);
        userRepository.save(user);
        return true;
    }

    public boolean saveUser(User user){
        userRepository.save(user);
        return true;
    }


}
