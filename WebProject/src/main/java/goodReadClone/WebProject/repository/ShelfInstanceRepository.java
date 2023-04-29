package goodReadClone.WebProject.repository;

import goodReadClone.WebProject.entity.ShelfInstance;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface ShelfInstanceRepository extends JpaRepository<ShelfInstance, Long>{
}