package goodReadClone.WebProject.repository;

import goodReadClone.WebProject.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByUsername(String auth);

    Set<Author> findAllByUsernameContainsIgnoreCase(String auth);
}