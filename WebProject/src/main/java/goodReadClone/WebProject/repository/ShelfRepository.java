package goodReadClone.WebProject.repository;

import goodReadClone.WebProject.entity.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShelfRepository extends JpaRepository<Shelf, Long>{
    Optional<Shelf> findByName(String name);
}
