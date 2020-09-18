package by.s0mmelier.repository;

import by.s0mmelier.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {

    void deleteById(long id);
}
