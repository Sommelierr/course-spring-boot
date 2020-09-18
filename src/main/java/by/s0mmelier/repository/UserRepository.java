package by.s0mmelier.repository;

import java.util.Optional;

import by.s0mmelier.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	User findById(long id);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
