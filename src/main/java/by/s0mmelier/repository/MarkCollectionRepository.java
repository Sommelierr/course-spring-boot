package by.s0mmelier.repository;

import by.s0mmelier.collections.MarkCollection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarkCollectionRepository  extends JpaRepository<MarkCollection, Long> {
    List<MarkCollection> findAllByUserId(long id);
    MarkCollection findById(long id);
}
