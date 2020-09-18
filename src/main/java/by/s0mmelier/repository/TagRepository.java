package by.s0mmelier.repository;

import by.s0mmelier.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long> {

    Tag findById(long id);

    Tag findByName(String name);
}
