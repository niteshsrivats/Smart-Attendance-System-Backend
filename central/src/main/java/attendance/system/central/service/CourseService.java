package attendance.system.central.service;

import attendance.system.central.exceptions.BadRequestException;
import attendance.system.central.exceptions.DuplicateEntityException;
import attendance.system.central.exceptions.EntityNotFoundException;
import attendance.system.central.models.entities.Course;
import attendance.system.central.models.entities.Department;
import attendance.system.central.models.entities.Teacher;
import attendance.system.central.repositories.postgres.CourseRepository;
import attendance.system.central.repositories.postgres.DepartmentRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static attendance.system.central.utils.IdUtils.generateCourseId;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, DepartmentRepository departmentRepository) {
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public List<Course> getCourses(Integer year) {
        if (year == null) {
            return courseRepository.findAll();
        } else {
            ArrayList<Course> courses = new ArrayList<>();
            for (Course course : courseRepository.findAll()) {
                if (course.getYear() == year) {
                    courses.add(course);
                }
            }
            return courses;
        }
    }

    @Transactional
    public Course getCourseById(String id) {
        if (id == null) {
            throw new BadRequestException("Course id cannot be null.");
        }
        return courseRepository.findCourseById(id).orElseThrow(() -> new EntityNotFoundException(Course.class, id));
    }

    @Transactional
    public List<Teacher> getCourseTeachers(String id) {
        Course course = getCourseById(id);
        Hibernate.initialize(course.getTeachers());
        ArrayList<Teacher> teachers = new ArrayList<>();
        for (Teacher teacher : course.getTeachers()) {
            if (teacher.getValid()) {
                Hibernate.initialize(teacher.getCourses());
                Hibernate.initialize(teacher.getSections());
                teachers.add(teacher);
            }
        }
        return teachers;
    }

    @Transactional
    public Course addCourse(Course course) {
        if (course.getDepartment() == null) {
            throw new BadRequestException("Department cannot be null");
        }
        try {
            course.setId(generateCourseId(course));
            getCourseById(course.getId());
            throw new DuplicateEntityException(Course.class, course.getId());
        } catch (EntityNotFoundException e) {
            course.setDepartment(departmentRepository.findDepartmentById(course.getDepartment().getId()).orElseThrow(
                    () -> new EntityNotFoundException(Department.class, course.getDepartment().getId())));
            course.setValid(true);
        }
        return courseRepository.save(course);
    }

    @Transactional
    public void deleteCourse(String id) {
        Course course = getCourseById(id);
        course.setValid(false);
        courseRepository.save(course);
    }
}
