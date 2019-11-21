package attendance.system.central.repositories.postgres;

import attendance.system.central.models.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findById(String id);
}
