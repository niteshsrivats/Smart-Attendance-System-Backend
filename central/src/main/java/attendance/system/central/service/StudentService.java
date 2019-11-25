package attendance.system.central.service;

import attendance.system.central.exceptions.BadRequestException;
import attendance.system.central.exceptions.DuplicateEntityException;
import attendance.system.central.exceptions.EntityNotFoundException;
import attendance.system.central.models.constants.EntityType;
import attendance.system.central.models.entities.Course;
import attendance.system.central.models.entities.Department;
import attendance.system.central.models.entities.Section;
import attendance.system.central.models.entities.Student;
import attendance.system.central.repositories.postgres.CourseRepository;
import attendance.system.central.repositories.postgres.DepartmentRepository;
import attendance.system.central.repositories.postgres.SectionRepository;
import attendance.system.central.repositories.postgres.StudentRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Service
public class StudentService {

    private final AuthorizationEntityService authorizationEntityService;
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final SectionRepository sectionRepository;
    private final CourseRepository courseRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public StudentService(AuthorizationEntityService authorizationEntityService, StudentRepository studentRepository, DepartmentRepository departmentRepository, SectionRepository sectionRepository, CourseRepository courseRepository, PasswordEncoder passwordEncoder) {
        this.authorizationEntityService = authorizationEntityService;
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
        this.sectionRepository = sectionRepository;
        this.courseRepository = courseRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Student getStudent(String id) {
        Student student = getStudentById(id);
        Hibernate.initialize(student.getSections());
        return student;
    }

    private Student getStudentById(String id) {
        if (id == null) {
            throw new BadRequestException("Student id cannot be null.");
        }
        return studentRepository.findStudentByEntity_Id(id).orElseThrow(() -> new EntityNotFoundException(Student.class, id));
    }

    @Transactional
    public List<Section> getStudentSections(String id) {
        Student student = getStudentById(id);
        Hibernate.initialize(student.getSections());
        return student.getSections();
    }

    @Transactional
    public Student addStudent(Student student) {
        if (authorizationEntityService.entityExists(student.getId())) {
            throw new DuplicateEntityException(Student.class, student.getId());
        }
        student.setDepartment(departmentRepository.findDepartmentById(student.getDepartment().getId()).orElseThrow(
                () -> new EntityNotFoundException(Department.class, student.getDepartment().getId())));
        student.getEntity().setPassword(passwordEncoder.encode(student.getEntity().getPassword()));
        student.getEntity().setType(EntityType.STUDENT);
        return studentRepository.save(student);
    }

    @Transactional
    public Student addSectionToStudent(String id, Section section) {
        Student student = getStudentById(id);
        student.getSections().add(sectionRepository.findSectionById(section.getId()).orElseThrow(
                () -> new EntityNotFoundException(Section.class, section.getId())));
        return student;
    }

    @Transactional
    public Student addCourseToStudent(String id, Course course) {
        Student student = getStudentById(id);
        student.getCourses().add(courseRepository.findCourseById(course.getId()).orElseThrow(
                () -> new EntityNotFoundException(Course.class, course.getId())));
        return student;
    }
}
