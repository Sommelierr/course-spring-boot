package by.s0mmelier.service;

import by.s0mmelier.Dto.UserDto;
import by.s0mmelier.collections.BookCollection;
import by.s0mmelier.models.User;
import by.s0mmelier.repository.UserRepository;
import by.s0mmelier.collections.AlcoholCollection;
import by.s0mmelier.collections.MarkCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookCollectionService bookCollectionService;

    public User getUserById(long id){
        return userRepository.findById(id);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
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

    public void deleteUserById(long id){
        userRepository.deleteById(id);
    }

    public List<UserDto> convertToUsersDto(List<User> users){
        List<UserDto> usersDto = new ArrayList<>();
        for(User user : users) {
            UserDto userDto = new UserDto(user);
            usersDto.add(userDto);
        }
        return usersDto;
    }


}
