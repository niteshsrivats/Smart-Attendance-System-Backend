package attendance.system.central.service;

import attendance.system.central.exceptions.DuplicateEntityException;
import attendance.system.central.exceptions.EntityNotFoundException;
import attendance.system.central.models.constants.UserType;
import attendance.system.central.models.entities.Device;
import attendance.system.central.repositories.postgres.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Service
public class DeviceService {

    private final AuthorizationEntityService authorizationEntityService;
    private final DeviceRepository deviceRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DeviceService(AuthorizationEntityService authorizationEntityService, DeviceRepository deviceRepository, PasswordEncoder passwordEncoder) {
        this.authorizationEntityService = authorizationEntityService;
        this.deviceRepository = deviceRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Device> getDevices() {
        return deviceRepository.findAll();
    }

    public Device getDeviceById(String id) {
        return deviceRepository.findDeviceByEntity_Id(id).orElseThrow(() -> new EntityNotFoundException(Device.class, id));
    }

    public Device addDevice(Device teacher) {
        if (authorizationEntityService.entityExists(teacher.getEntity().getId())) {
            throw new DuplicateEntityException(Device.class, teacher.getEntity().getId());
        }
        teacher.getEntity().setPassword(passwordEncoder.encode(teacher.getEntity().getPassword()));
        teacher.getEntity().setUserType(UserType.STUDENT);
        return deviceRepository.save(teacher);
    }
}
