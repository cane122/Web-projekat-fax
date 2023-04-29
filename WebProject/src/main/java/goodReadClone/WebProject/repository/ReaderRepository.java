package goodReadClone.WebProject.repository;

import goodReadClone.WebProject.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
}