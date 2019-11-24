package attendance.system.central.repositories.postgres;

import attendance.system.central.models.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findCourseById(String id);
}
