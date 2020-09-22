package by.s0mmelier.repository;

import by.s0mmelier.models.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Integer> {
    Theme findByName(String name);

    boolean existsByName(String name);
}
