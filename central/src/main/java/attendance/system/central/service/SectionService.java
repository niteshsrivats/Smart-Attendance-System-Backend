package attendance.system.central.service;

import attendance.system.central.exceptions.BadRequestException;
import attendance.system.central.exceptions.DuplicateEntityException;
import attendance.system.central.exceptions.EntityNotFoundException;
import attendance.system.central.models.entities.*;
import attendance.system.central.repositories.postgres.CourseRepository;
import attendance.system.central.repositories.postgres.DepartmentRepository;
import attendance.system.central.repositories.postgres.SectionRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static attendance.system.central.utils.IdUtils.generateSectionId;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Service
public class SectionService {

    private final SectionRepository sectionRepository;
    private final DepartmentRepository departmentRepository;
    private final CourseRepository courseRepository;
    private final TeacherService teacherService;
    private final StudentService studentService;

    @Autowired
    public SectionService(SectionRepository sectionRepository, DepartmentRepository departmentRepository, CourseRepository courseRepository, TeacherService teacherService, StudentService studentService) {
        this.sectionRepository = sectionRepository;
        this.departmentRepository = departmentRepository;
        this.courseRepository = courseRepository;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    public List<Section> getSections() {
        return this.sectionRepository.findAll();
    }

    public Section getSectionById(String id) {
        if (id == null) {
            throw new BadRequestException("Section id cannot be null.");
        }
        return this.sectionRepository.findSectionById(id).orElseThrow(() -> new EntityNotFoundException(Section.class, id));
    }

    @Transactional
    public Set<Student> getSectionStudents(String sectionId) {
        Section section = getSectionById(sectionId);
        for (Student student : section.getStudents()) {
            Hibernate.initialize(student.getSections());
        }
        return section.getStudents();
    }

    @Transactional
    public Map<Course, Teacher> getCourseTeacherMap(String sectionId) {
        Section section = getSectionById(sectionId);
        Hibernate.initialize(section.getCourseTeacherMap());
        for (Course course : section.getCourseTeacherMap().keySet()) {
            Hibernate.initialize(section.getCourseTeacherMap().get(course).getSections());
            Hibernate.initialize(section.getCourseTeacherMap().get(course).getCourses());
        }
        return section.getCourseTeacherMap();
    }

    public Section addSection(Section section) {
        try {
            section.setId(generateSectionId(section));
            getSectionById(section.getId());
            throw new DuplicateEntityException(Section.class, section.getId());
        } catch (EntityNotFoundException e) {
            section.setDepartment(departmentRepository.findDepartmentById(section.getDepartment().getId()).orElseThrow(
                    () -> new EntityNotFoundException(Department.class, section.getDepartment().getId())));
            return sectionRepository.save(section);
        }
    }

    @Transactional
    public Section addCourseTeacherPair(String id, String teacherId, Course course) {
        Section section = getSectionById(id);
        Course newCourse = courseRepository.findCourseById(course.getId()).orElseThrow(() -> new EntityNotFoundException(Course.class, course.getId()));
        Teacher teacher = teacherService.getTeacher(teacherId);
        section.getCourseTeacherMap().put(newCourse, teacher);
        Hibernate.initialize(section.getStudents());
        if (!teacher.getCourses().contains(newCourse)) {
            teacherService.addCourseToTeacher(teacherId, newCourse);
        }
        // TODO add course for student using student service
        return sectionRepository.save(section);
    }
}
