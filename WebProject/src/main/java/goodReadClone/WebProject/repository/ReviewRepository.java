package goodReadClone.WebProject.repository;

import goodReadClone.WebProject.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
}
