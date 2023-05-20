package goodReadClone.WebProject.repository;

import goodReadClone.WebProject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {
}
