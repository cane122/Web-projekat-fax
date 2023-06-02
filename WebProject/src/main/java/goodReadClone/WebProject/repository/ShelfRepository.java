package goodReadClone.WebProject.repository;

import goodReadClone.WebProject.entity.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShelfRepository extends JpaRepository<Shelf, Long>{
    Optional<Shelf> findByName(String name);
}
