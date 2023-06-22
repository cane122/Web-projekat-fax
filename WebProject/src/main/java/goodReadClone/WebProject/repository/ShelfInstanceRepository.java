package goodReadClone.WebProject.repository;

import goodReadClone.WebProject.entity.ShelfInstance;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelfInstanceRepository extends JpaRepository<ShelfInstance, Long>{
    Long getReviewById(Long idShelfInstance);

    Long deleteReviewById(Long idShelfInstance);

    @Transactional
    Long deleteBook_ReviewById(Long idShelfInstance);
}