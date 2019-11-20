package attendance.system.central.repositories.postgres;

import attendance.system.central.models.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    Optional<Device> findDeviceByEntity_Id(String id);
}
