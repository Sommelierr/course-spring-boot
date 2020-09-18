package by.s0mmelier.repository;

import java.util.Optional;

import by.s0mmelier.models.ERole;
import by.s0mmelier.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);

	boolean existsByName(ERole role);
}
