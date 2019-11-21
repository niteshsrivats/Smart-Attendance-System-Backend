package attendance.system.central.service;

import attendance.system.central.exceptions.DuplicateEntityException;
import attendance.system.central.exceptions.EntityNotFoundException;
import attendance.system.central.models.entities.Section;
import attendance.system.central.models.entities.Teacher;
import attendance.system.central.repositories.postgres.SectionRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Service
public class SectionService {

    private final SectionRepository sectionRepository;

    @Autowired
    public SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public List<Section> getSections() {
        return this.sectionRepository.findAll();
    }

    public Section getSectionById(String id) {
        return this.sectionRepository.findSectionById(id).orElseThrow(() -> new EntityNotFoundException(Section.class, id));
    }

    @Transactional
    public Set<Teacher> getSectionTeachers(String sectionId) {
        Section section = getSectionById(sectionId);
        Hibernate.initialize(section.getTeachers());
        return section.getTeachers();
    }

    @Transactional
    public Teacher getSectionTeacherById(String sectionId, String teacherId) {
        for (Teacher teacher : getSectionTeachers(sectionId)) {
            if (teacher.getId().equals(teacherId)) {
                return teacher;
            }
        }
        throw new EntityNotFoundException(Teacher.class, teacherId);
    }

    public Section addSection(Section section) {
        try {
            getSectionById(section.getId());
            throw new DuplicateEntityException(Section.class, section.getId());
        } catch (EntityNotFoundException e) {
            return sectionRepository.save(section);
        }
    }
}
