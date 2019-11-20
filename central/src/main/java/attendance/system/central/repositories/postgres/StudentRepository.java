package attendance.system.central.repositories.postgres;

import attendance.system.central.models.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findStudentByEntity_Id(String id);
}
