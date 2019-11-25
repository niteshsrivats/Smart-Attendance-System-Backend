package attendance.system.central.service;

import attendance.system.central.exceptions.BadRequestException;
import attendance.system.central.exceptions.DuplicateEntityException;
import attendance.system.central.exceptions.EntityNotFoundException;
import attendance.system.central.models.constants.EntityType;
import attendance.system.central.models.entities.Device;
import attendance.system.central.models.entities.Section;
import attendance.system.central.repositories.postgres.DeviceRepository;
import attendance.system.central.repositories.postgres.SectionRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Service
public class DeviceService {

    private final AuthorizationEntityService authorizationEntityService;
    private final DeviceRepository deviceRepository;
    private final PasswordEncoder passwordEncoder;
    private final SectionRepository sectionRepository;

    @Autowired
    public DeviceService(AuthorizationEntityService authorizationEntityService, DeviceRepository deviceRepository, PasswordEncoder passwordEncoder, SectionRepository sectionRepository) {
        this.authorizationEntityService = authorizationEntityService;
        this.deviceRepository = deviceRepository;
        this.passwordEncoder = passwordEncoder;
        this.sectionRepository = sectionRepository;
    }

    @Transactional
    public Device getDevice(String id) {
        Device device = getDeviceById(id);
        Hibernate.initialize(device.getSection());
        return device;
    }

    @Transactional
    private Device getDeviceById(String id) {
        if (id == null) {
            throw new BadRequestException("Device id cannot be null.");
        }
        return deviceRepository.findDeviceByEntity_Id(id).orElseThrow(() -> new EntityNotFoundException(Device.class, id));
    }

    @Transactional
    public Device addDevice(Device device) {
        if (authorizationEntityService.entityExists(device.getId())) {
            throw new DuplicateEntityException(Device.class, device.getEntity().getId());
        }
        if (device.getSection() != null) {
            device.setSection(sectionRepository.findSectionById(device.getSection().getId()).orElseThrow(
                    () -> new EntityNotFoundException(Section.class, device.getSection().getId())));
        }
        device.getEntity().setPassword(passwordEncoder.encode(device.getEntity().getPassword()));
        device.getEntity().setType(EntityType.DEVICE);
        return deviceRepository.save(device);
    }

    @Transactional
    public Device updateSection(String id, Section section) {
        Device device = getDeviceById(id);
        Section newSection = sectionRepository.findSectionById(section.getId()).orElseThrow(
                () -> new EntityNotFoundException(Section.class, section.getId()));
        device.setSection(newSection);
        return deviceRepository.save(device);
    }
}
