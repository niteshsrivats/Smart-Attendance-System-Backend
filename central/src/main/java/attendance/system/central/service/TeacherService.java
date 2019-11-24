package attendance.system.central.service;

import attendance.system.central.exceptions.BadRequestException;
import attendance.system.central.exceptions.DuplicateEntityException;
import attendance.system.central.exceptions.EntityNotFoundException;
import attendance.system.central.models.constants.EntityType;
import attendance.system.central.models.entities.Course;
import attendance.system.central.models.entities.Department;
import attendance.system.central.models.entities.Section;
import attendance.system.central.models.entities.Teacher;
import attendance.system.central.repositories.postgres.CourseRepository;
import attendance.system.central.repositories.postgres.DepartmentRepository;
import attendance.system.central.repositories.postgres.SectionRepository;
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
    private final DepartmentRepository departmentRepository;
    private final SectionRepository sectionRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public TeacherService(AuthorizationEntityService authorizationEntityService, TeacherRepository teacherRepository, PasswordEncoder passwordEncoder, DepartmentRepository departmentRepository, SectionRepository sectionRepository, CourseRepository courseRepository) {
        this.authorizationEntityService = authorizationEntityService;
        this.teacherRepository = teacherRepository;
        this.passwordEncoder = passwordEncoder;
        this.departmentRepository = departmentRepository;
        this.sectionRepository = sectionRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public Teacher getTeacher(String id) {
        Teacher teacher = getTeacherById(id);
        Hibernate.initialize(teacher.getSections());
        Hibernate.initialize(teacher.getCourses());
        return teacher;
    }

    private Teacher getTeacherById(String id) {
        if (id == null) {
            throw new BadRequestException("Teacher id cannot be null.");
        }
        return teacherRepository.findTeacherByEntity_Id(id).orElseThrow(() -> new EntityNotFoundException(Teacher.class, id));
    }

    @Transactional
    public List<Section> getTeacherSections(String id) {
        Teacher teacher = getTeacherById(id);
        Hibernate.initialize(teacher.getSections());
        return teacher.getSections();
    }

    @Transactional
    public Set<Course> getTeacherCourses(String id) {
        Teacher teacher = getTeacherById(id);
        Hibernate.initialize(teacher.getCourses());
        return teacher.getCourses();
    }

    public Teacher addTeacher(Teacher teacher) {
        if (authorizationEntityService.entityExists(teacher.getId())) {
            throw new DuplicateEntityException(Teacher.class, teacher.getId());
        }
        teacher.setDepartment(departmentRepository.findDepartmentById(teacher.getDepartment().getId()).orElseThrow(
                () -> new EntityNotFoundException(Department.class, teacher.getDepartment().getId())));
        teacher.getEntity().setPassword(passwordEncoder.encode(teacher.getEntity().getPassword()));
        teacher.getEntity().setType(EntityType.TEACHER);
        teacher.setValid(true);
        return teacherRepository.save(teacher);
    }

    @Transactional
    public Teacher addCourseToTeacher(String id, Course course) {
        Teacher teacher = getTeacherById(id);
        teacher.getCourses().add(courseRepository.findCourseById(course.getId()).orElseThrow(() -> new EntityNotFoundException(Course.class, course.getId())));
        Hibernate.initialize(teacher.getSections());
        return teacherRepository.save(teacher);
    }

    @Transactional
    public Teacher addSectionToTeacher(String id, Section section) {
        Teacher teacher = getTeacherById(id);
        teacher.getSections().add(sectionRepository.findSectionById(section.getId()).orElseThrow(
                () -> new EntityNotFoundException(Section.class, section.getId())));
        Hibernate.initialize(teacher.getCourses());
        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(String id) {
        Teacher teacher = getTeacherById(id);
        teacher.setValid(false);
        teacherRepository.save(teacher);
    }
}
