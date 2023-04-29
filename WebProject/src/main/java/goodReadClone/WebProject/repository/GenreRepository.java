package goodReadClone.WebProject.repository;

import goodReadClone.WebProject.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long>{
}
