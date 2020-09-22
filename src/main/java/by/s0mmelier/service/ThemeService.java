package by.s0mmelier.service;

import by.s0mmelier.models.Theme;
import by.s0mmelier.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThemeService {

    @Autowired
    ThemeRepository themeRepository;

    public Theme getByName(String name) { return themeRepository.findByName(name);}

    public boolean existsByName(String name){
        return themeRepository.existsByName(name);
    }

    public void save(Theme theme){
        themeRepository.save(theme);
    }
}
