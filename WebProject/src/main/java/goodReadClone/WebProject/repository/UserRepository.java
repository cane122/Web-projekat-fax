package goodReadClone.WebProject.repository;

import goodReadClone.WebProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}