package by.s0mmelier.repository;

import by.s0mmelier.models.Alcohol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlcoholRepository extends JpaRepository<Alcohol, Long> {
    void deleteById(long id);
}
