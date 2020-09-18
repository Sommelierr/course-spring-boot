package by.s0mmelier.service;

import by.s0mmelier.models.Mark;
import by.s0mmelier.repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MarkService {
    @Autowired
    MarkRepository markRepository;

    Optional<Mark> getMark(long id){
        return markRepository.findById(id);
    }

}
