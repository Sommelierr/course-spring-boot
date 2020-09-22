package by.s0mmelier.repository;


import by.s0mmelier.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    void deleteById(long id);
    Book findTopByOrderByIdDesc();
}
