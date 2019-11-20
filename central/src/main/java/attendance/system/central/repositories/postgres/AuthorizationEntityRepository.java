package attendance.system.central.repositories.postgres;

import attendance.system.central.models.entities.AuthorizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Repository
public interface AuthorizationEntityRepository extends JpaRepository<AuthorizationEntity, Long> {

    Optional<AuthorizationEntity> findById(String id);

    Boolean existsById(String id);
}
