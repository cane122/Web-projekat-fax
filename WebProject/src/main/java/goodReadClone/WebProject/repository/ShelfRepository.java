package goodReadClone.WebProject.repository;

import goodReadClone.WebProject.entity.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface ShelfRepository extends JpaRepository<Shelf, Long>{
}
