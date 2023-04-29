package goodReadClone.WebProject.repository;

import goodReadClone.WebProject.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}