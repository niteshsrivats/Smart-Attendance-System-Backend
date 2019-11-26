package attendance.system.central.service;

import attendance.system.central.exceptions.DuplicateEntityException;
import attendance.system.central.exceptions.EntityNotFoundException;
import attendance.system.central.models.entities.Department;
import attendance.system.central.models.entities.Room;
import attendance.system.central.models.entities.Section;
import attendance.system.central.repositories.postgres.DepartmentRepository;
import attendance.system.central.repositories.postgres.RoomRepository;
import attendance.system.central.repositories.postgres.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static attendance.system.central.utils.IdUtils.generateRoomId;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final DepartmentRepository departmentRepository;
    private final SectionRepository sectionRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, DepartmentRepository departmentRepository, SectionRepository sectionRepository) {
        this.roomRepository = roomRepository;
        this.departmentRepository = departmentRepository;
        this.sectionRepository = sectionRepository;
    }

    @Transactional
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    @Transactional
    public Room getRoomById(String id) {
        return roomRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Room.class, id));
    }

    @Transactional
    public Room addRoom(Room room) {
        room.setId(generateRoomId(room));
        try {
            getRoomById(room.getId());
            throw new DuplicateEntityException(Room.class, room.getId());
        } catch (EntityNotFoundException e) {
            room.setDepartment(departmentRepository.findDepartmentById(room.getDepartment().getId()).orElseThrow(
                    () -> new EntityNotFoundException(Department.class, room.getDepartment().getId())));
        }
        return roomRepository.save(room);
    }

    @Transactional
    public Room updateSection(String id, Section section) {
        Room room = getRoomById(id);
        Section newSection = sectionRepository.findSectionById(section.getId()).orElseThrow(
                () -> new EntityNotFoundException(Section.class, section.getId()));
        newSection.setRoom(room.getRoom());
        room.setSection(sectionRepository.save(newSection));
        return roomRepository.save(room);
    }
}
