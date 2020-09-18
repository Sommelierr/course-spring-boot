package by.s0mmelier.repository;

import by.s0mmelier.collections.BookCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookCollectionRepository extends JpaRepository<BookCollection, Long> {
    List<BookCollection> findAllByUserId(long id);
    BookCollection findById(long id);
}
