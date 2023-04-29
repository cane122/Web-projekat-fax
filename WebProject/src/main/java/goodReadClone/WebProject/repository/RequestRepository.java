package goodReadClone.WebProject.repository;

import goodReadClone.WebProject.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{
}
