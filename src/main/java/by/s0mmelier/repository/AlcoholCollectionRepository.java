package by.s0mmelier.repository;

import by.s0mmelier.collections.AlcoholCollection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlcoholCollectionRepository extends JpaRepository<AlcoholCollection, Long> {
    List<AlcoholCollection> findAllByUserId(long id);
    AlcoholCollection findById(long id);
}
