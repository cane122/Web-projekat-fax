package goodReadClone.WebProject.repository;

import goodReadClone.WebProject.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}