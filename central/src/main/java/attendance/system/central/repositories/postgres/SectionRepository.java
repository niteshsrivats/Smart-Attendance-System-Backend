package attendance.system.central.repositories.postgres;

import attendance.system.central.models.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {

    Optional<Section> findSectionById(String id);
}
