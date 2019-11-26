package attendance.system.central.service;

import attendance.system.central.exceptions.BadRequestException;
import attendance.system.central.exceptions.DuplicateEntityException;
import attendance.system.central.exceptions.EntityNotFoundException;
import attendance.system.central.models.entities.*;
import attendance.system.central.repositories.postgres.DepartmentRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(String id) {
        if (id == null) {
            throw new BadRequestException("Department id cannot be null.");
        }
        return departmentRepository.findDepartmentById(id).orElseThrow(() -> new EntityNotFoundException(Department.class, id));
    }

    @Transactional
    public Collection<Course> getDepartmentCourses(String id, Byte semester) {
        Department department = getDepartmentById(id);
        Hibernate.initialize(department.getCourses());
        if (semester == null) {
            return department.getCourses();
        }

        List<Course> courses = new ArrayList<>();
        for (Course course : department.getCourses()) {
            if (course.getValid() && course.getSemester().equals(semester)) {
                courses.add(course);
            }
        }
        return courses;
    }

    @Transactional
    public Collection<Section> getDepartmentSections(String id, Byte semester) {
        Department department = getDepartmentById(id);
        Hibernate.initialize(department.getSections());
        if (semester == null) {
            return department.getSections();
        }

        List<Section> sections = new ArrayList<>();
        for (Section section : department.getSections()) {
            if (section.getSemester().equals(semester)) {
                sections.add(section);
            }
        }
        return sections;
    }

    @Transactional
    public Collection<Student> getDepartmentStudents(String id, Integer year) {
        Department department = getDepartmentById(id);
        Hibernate.initialize(department.getStudents());
        if (year == null) {
            return department.getStudents();
        }


        List<Student> students = new ArrayList<>();
        for (Student student : department.getStudents()) {
            Hibernate.initialize(student.getSections());
            if (student.getGraduationYear() >= year && student.getGraduationYear() <= year + 4) {
                students.add(student);
            }
        }
        return students;
    }

    @Transactional
    public List<Teacher> getDepartmentTeachers(String id) {
        Department department = getDepartmentById(id);
        Hibernate.initialize(department.getTeachers());
        List<Teacher> teachers = new ArrayList<>();
        for (Teacher teacher : department.getTeachers()) {
            if (teacher.getValid()) {
                Hibernate.initialize(teacher.getCourses());
                Hibernate.initialize(teacher.getSections());
                teachers.add(teacher);
            }
        }
        return teachers;
    }

    @Transactional
    public Set<Room> getDepartmentRooms(String id) {
        Department department = getDepartmentById(id);
        Hibernate.initialize(department.getRooms());
        return department.getRooms();
    }

    @Transactional
    public Department addDepartment(Department department) {
        try {
            getDepartmentById(department.getId());
            throw new DuplicateEntityException(Department.class, department.getId());
        } catch (EntityNotFoundException e) {
            return departmentRepository.save(department);
        }
    }
}
