package attendance.system.central.service;

import attendance.system.central.exceptions.DuplicateEntityException;
import attendance.system.central.exceptions.EntityNotFoundException;
import attendance.system.central.models.constants.EntityType;
import attendance.system.central.models.entities.Section;
import attendance.system.central.models.entities.Teacher;
import attendance.system.central.repositories.postgres.TeacherRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Service
public class TeacherService {

    private final AuthorizationEntityService authorizationEntityService;
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;
    private final SectionService sectionService;

    @Autowired
    public TeacherService(AuthorizationEntityService authorizationEntityService, TeacherRepository teacherRepository, PasswordEncoder passwordEncoder, SectionService sectionService) {
        this.authorizationEntityService = authorizationEntityService;
        this.teacherRepository = teacherRepository;
        this.passwordEncoder = passwordEncoder;
        this.sectionService = sectionService;
    }

    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(String id) {
        return teacherRepository.findTeacherByEntity_Id(id).orElseThrow(() -> new EntityNotFoundException(Teacher.class, id));
    }

    @Transactional
    public Set<Section> getTeacherSections(String id) {
        Teacher teacher = getTeacherById(id);
        Hibernate.initialize(teacher.getSections());
        return teacher.getSections();
    }

    @Transactional
    public Section getTeacherSectionById(String teacherId, String sectionId) {
        for (Section section : getTeacherById(teacherId).getSections()) {
            if (section.getId().equals(sectionId)) {
                return section;
            }
        }
        throw new EntityNotFoundException(Section.class, sectionId);
    }

    public Teacher addTeacher(Teacher teacher) {
        if (authorizationEntityService.entityExists(teacher.getId())) {
            throw new DuplicateEntityException(Teacher.class, teacher.getId());
        }
        teacher.getEntity().setPassword(passwordEncoder.encode(teacher.getEntity().getPassword()));
        teacher.getEntity().setEntityType(EntityType.TEACHER);
        return teacherRepository.save(teacher);
    }

    @Transactional
    public Teacher addSectionToTeacher(String id, String sectionId) {
        Teacher teacher = getTeacherById(id);
        teacher.getSections().add(sectionService.getSectionById(sectionId));
        return teacherRepository.save(teacher);
    }
}
