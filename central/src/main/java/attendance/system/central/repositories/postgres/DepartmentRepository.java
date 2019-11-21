package attendance.system.central.repositories.postgres;

import attendance.system.central.models.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findDepartmentById(String id);
}
